package com.myproject.libmgmt.db;

import com.myproject.libmgmt.model.BookLoan;
import com.myproject.libmgmt.model.OverDueBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookLoanDBService {

    public void createBookLoanTable() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE BOOKLOAN " +
                    "(BOOKID              INT               NOT     NULL," +
                    " ROLLNO         INT                  NOT NULL, " +
                    " ISSUEDATE          DATE                  NOT  NULL, " +
                    " DUEDATE           DATE                NOT NULL, " +
                    " RETURNDATE           DATE                 NULL," +
                    " FINE           INT                 NULL," +
                    " PRIMARY KEY(BOOKID, ROLLNO) )";


            System.out.println("Create BOOKLOAN table sql:" + sql);

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Error while creating the bookloan table");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("BookLoan table created successfully");

    }


    public BookLoan selectBookLoanByBookId(int bookId) throws Exception {

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        BookLoan bookLoan = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM BOOKLOAN WHERE BOOKID=" + "" + bookId + "";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                bookLoan = new BookLoan();
                bookLoan.bookId = rs.getInt("BOOKID");
                bookLoan.rollNo = rs.getInt("ROLLNO");
                bookLoan.dueDate = rs.getString("DUEDATE");
            }


            System.out.println("SQL QUERY:" + sql);
            rs = stmt.executeQuery(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Error while selectBookLoanByBookId");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw e;

        }

        return bookLoan;
    }


    public void updateBookLoan(BookLoan bookLoan) throws Exception {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        BookLoan result = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "UPDATE BOOKLOAN SET RETURNDATE='"  + bookLoan.returnDate + "', FINE=" + bookLoan.fine + "  WHERE BOOKID=" + bookLoan.bookId + "";

            System.out.println("SQL:" + sql);

            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println("Error while update record in bookloan table");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            throw e;

        }

    }


    public void insertBookLoan(int bookid, int rollno, String issuedate, String duedate) {

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "INSERT INTO BOOKLOAN (BOOKID,ROLLNO,ISSUEDATE,DUEDATE) VALUES" +
                    "(" + bookid + ",'" + rollno + "','" + issuedate + "','" + duedate + "')";

            System.out.println("SQL:" + sql);

            stmt.execute(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println("Error while inserting record in bookloan table");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

    }

    public List<OverDueBook> getOverDueBooks(String overDueDate) throws Exception {

        Connection c = null;
        Statement stmt = null;
        List<OverDueBook> overDueBooks= new ArrayList<OverDueBook>();

        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);

            stmt = c.createStatement();



            String sql = "select bl.BOOKID, bl.ROLLNO, bk.BOOKNAME, st.FIRSTNAME, st.LASTNAME," +
                    " bl.ISSUEDATE, bl.DUEDATE " +
                    "from BookLoan bl, Student st, Book bk where bl.rollno = st.rollno and  bk.bookid = bl.bookid " +
                    " and DUEDATE >= '" + overDueDate + "'";
            System.out.println("OVEDUE SQL:"+sql);

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                OverDueBook overDueBook = new OverDueBook(rs.getInt("BOOKID"), rs.getString("BOOKNAME"), rs.getInt("ROLLNO"),
                        rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                        rs.getString("ISSUEDATE"),rs.getString("DUEDATE"));

                overDueBooks.add(overDueBook);
            }


            System.out.println("SQL QUERY:" + sql);
            rs = stmt.executeQuery(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Error while selectBookLoanByBookId");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw e;

        }

        return overDueBooks;

    }
}

      
      