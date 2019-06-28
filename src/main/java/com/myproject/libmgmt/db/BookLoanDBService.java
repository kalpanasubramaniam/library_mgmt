package com.myproject.libmgmt.db;
import com.myproject.libmgmt.model.Book;

import java.sql.*;

// EXAMPLES:
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class BookLoanDBService {

   public void	 createBookLoanTable() {
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
                        " PRIMARY KEY(BOOKID, ROLLNO) )" ;
                        

         System.out.println("Create BOOKLOAN table sql:"+sql);               

         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) { 
          System.err.println("Error while creating the bookloan table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }
      System.out.println("BookLoan table created successfully");

   }


   public ResultSet selectBookLoanByBookId(int bookId){

     Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         System.out.println("Opened database successfully");
       c.setAutoCommit(false);
         
         stmt = c.createStatement();
         String sql = "SELECT * FROM BOOKLOAN WHERE BOOKID=" + "'" + bookId + "'";

         System.out.println("SQL QUERY:"+sql);
         rs  = stmt.executeQuery(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
          System.err.println("Error while selectBookLoanByBookId");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

       return rs;
  }


  public void updateBookLoan(int bookid, int rollno, String returndate, int fine) {
      Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         c.setAutoCommit(false);

         stmt = c.createStatement();

         String sql = "UPDATE BOOKLOAN SET "+ rollno + ",'"  +  returndate + "','" + fine + "'  WHERE "+ bookid +"";

         System.out.println("SQL:"+sql);

         stmt.execute(sql);
         
         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
          System.err.println("Error while update record in bookloan table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }
  
  }

   
   
  public void insertBookLoan(int bookid , int rollno, String issuedate, String duedate){

      Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         c.setAutoCommit(false);

         stmt = c.createStatement();

         String sql = "INSERT INTO BOOKLOAN (BOOKID,ROLLNO,ISSUEDATE,DUEDATE) VALUES" +
          "("+ bookid + ",'"  +  rollno + "','" + issuedate + "','" +duedate + "')";

         System.out.println("SQL:"+sql);

         stmt.execute(sql);
         
         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
          System.err.println("Error while inserting record in bookloan table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

   }

}

      
      