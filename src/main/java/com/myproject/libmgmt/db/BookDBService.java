package com.myproject.libmgmt.db;
import com.myproject.libmgmt.model.Book;

import java.sql.*;

// EXAMPLES:
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class BookDBService {

   public void	 createBookTable() {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE BOOK " +
                        "(BOOKID              INT                    NOT NULL," +
                        " AUTHORNAME          TEXT                     NOT NULL, " +
                        " PRICE          INT                     NOT NULL, " +
                        " BOOKNAME           CHAR(50)                 NOT NULL, " +
                        " DEPARTMENT           CHAR(50)                 NOT NULL, " +
                        " BOOKTITLE              TEXT                     NOT NULL )"  ;

         System.out.println("Create BOOK table sql:"+sql);               

         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) { 
          System.err.println("Error while creating the book table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }
      System.out.println("Book table created successfully");

   }


   public ResultSet selectBookByBookId(int bookId){

     Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         System.out.println("Opened database successfully");
       c.setAutoCommit(false);
         
         stmt = c.createStatement();
         String sql = "SELECT * FROM BOOK WHERE BOOKID=" + "'" + bookId + "'";

         System.out.println("SQL QUERY:"+sql);
         rs  = stmt.executeQuery(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
          System.err.println("Error while selectBookByBookId");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

       return rs;
  }


   
   
  public void insertBook(int bookid , String authorname, int price, String bookname, String department, String booktitle){

      Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         c.setAutoCommit(false);

         stmt = c.createStatement();

         String sql = "INSERT INTO BOOK (BOOKID,AUTHORNAME,PRICE,BOOKNAME,DEPARTMENT,BOOKTITLE) VALUES" +
          "("+ bookid + ",'"  +  authorname + "'," + price + ",'" +bookname + "','" + department + "','" +booktitle + "');";

         System.out.println("SQL:"+sql);

         stmt.execute(sql);
         
         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
          System.err.println("Error while inserting record in book table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

   }

}

      
      