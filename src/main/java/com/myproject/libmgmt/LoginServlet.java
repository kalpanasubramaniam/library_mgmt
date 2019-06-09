package com.myproject.libmgmt;

import com.myproject.libmgmt.db.StudentDBService;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    StudentDBService dbService = new StudentDBService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            ResultSet resultSet = dbService.selectStudentByUserName(userName);
            if (resultSet == null) {
                response.setStatus(404);
                response.setContentType("text/html");
                response.getWriter().println("<h1>User not found</h1>");

            } else {
                resultSet.next();
                String userNameInDB = resultSet.getString("USERNAME");
                String passwordInDB = resultSet.getString("PASSWORD");

                if (userNameInDB == userName.trim() && passwordInDB == password.trim()) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/html");
                    response.getWriter().println("Login Successfull");
                } else {
                    response.setStatus(404);
                    response.setContentType("text/html");
                    response.getWriter().println("Invalid Valid User");
                }

            }
        } catch (Exception ex) {
            response.setStatus(500);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }


    }

}