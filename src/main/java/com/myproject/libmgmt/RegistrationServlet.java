package com.myproject.libmgmt;

import com.myproject.libmgmt.db.StudentDBService;
import com.myproject.libmgmt.model.Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class RegistrationServlet extends HttpServlet {

    StudentDBService dbService = new StudentDBService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Registration of the student");

        try {
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String userName = request.getParameter("username");
            int rollNo = Integer.parseInt(request.getParameter("rollno"));
            String password = request.getParameter("password");
            String emailId = request.getParameter("emailid");

            System.out.println("FirstName:" + firstName);
            System.out.println("LastName :" + lastName);

            Student st = dbService.selectStudentByRollNo(rollNo);

            if (st == null) {
                System.out.println("Inserting student record");
                dbService.insertStudent(firstName, lastName, rollNo, userName, password, emailId);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("Registration done successfully");
            } else {
                System.err.println("Student record alread exists");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/html");
                response.getWriter().println("Student already exists");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }

}