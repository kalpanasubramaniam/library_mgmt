package com.myproject.libmgmt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("Admin UserName:" + userName);
            System.out.println("Admin Password:" + password);

            if (userName.equalsIgnoreCase("admin") && password.equals("AngelAdmin123")) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("Login Successfull");
            } else {
                response.setStatus(404);
                response.setContentType("text/html");
                response.getWriter().println("<h1>User not found</h1>");
            }


        } catch (Exception ex) {
            response.setStatus(500);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }


    }

}


