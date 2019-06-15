package com.myproject.libmgmt.db;
import com.myproject.libmgmt.model.Student;

import java.sql.*;

// EXAMPLES:
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class StudentDBService {

	public void createStudeTable(){

	  Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE STUDENT " +
                        "(ROLLNO              INT   PRIMARY KEY     NOT NULL," +
                        " FIRSTNAME           TEXT                     NOT NULL, " +
                        " LASTNAME            TEXT                     NOT NULL, " +
                        " USERNAME            CHAR(50)                 NOT NULL, " +
                        " PASSWORD            CHAR(50)                 NOT NULL, " +
                        " EMAIL               TEXT                     NOT NULL )"  ;


         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
      	 System.err.println("Error while creating the student table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }
      System.out.println("Student table created successfully");

	}


	public ResultSet selectStudentByRollNo(int rollNo){

	  Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         System.out.println("Opened database successfully");
		 c.setAutoCommit(false);
         
         stmt = c.createStatement();
         String sql = "SELECT * FROM STUDENT WHERE ROLLNO="+  + rollNo + ";";

          System.out.println("SQL QUERY:"+sql);


         rs  = stmt.executeQuery(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
      	 System.err.println("Error while selectStudentByRollNo");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

      return rs;
  }

    public Student selectStudentByUserName(String userName){

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        Student student = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM STUDENT WHERE USERNAME="+ "'" + userName + "';";

            System.out.println("SQL QUERY:"+sql);

            rs  = stmt.executeQuery(sql);

            if(rs.next()){
                student = new Student();
                student.rollNo = rs.getInt("ROLLNO");
                student.firstName = rs.getString("FIRSTNAME");
                student.lastName = rs.getString("LASTNAME");
                student.userName = rs.getString("USERNAME");
                student.password= rs.getString("PASSWORD");
                student.email = rs.getString("EMAIL");
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println("Error while selectStudentByUserName");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }


        return student;
    }

  public void insertStudent(String firstName, String lastName, int rollNo, String userName, String password, String emailId){

   	  Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:library_mgmt.db");
         c.setAutoCommit(false);

         stmt = c.createStatement();

         String sql = "INSERT INTO STUDENT (ROLLNO,FIRSTNAME,LASTNAME,USERNAME,PASSWORD,EMAIL) VALUES" +
          "("+ rollNo + ",'"  +  firstName + "','" + lastName + "','" +userName + "','" + password + "','" +emailId + "');";

         System.out.println("SQL:"+sql);

         stmt.execute(sql);
         
         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
      	 System.err.println("Error while inserting record in student table");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      }

   }

}
