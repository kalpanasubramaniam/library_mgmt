package com.myproject.libmgmt;

import com.myproject.libmgmt.db.BookLoanDBService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class BookLoanServlet extends HttpServlet {

    BookLoanDBService dbService = new BookLoanDBService();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("IssueBook of the student");

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        int rollNo = Integer.parseInt(request.getParameter("rollno"));
          
        Date currentDate=new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);
        Date  dueDate = c.getTime();
        String issueDate = sdf.format(currentDate);
        String returnDate = sdf.format(dueDate);

        System.out.println(sdf.format(dueDate));


        System.out.println("BookId:" + bookId);
        System.out.println("RollNo :" + rollNo);

        ResultSet rs = dbService.selectBookLoanByBookId(bookId);

        try {
            if (rs == null || !rs.next()) {
                System.out.println("already book issued");
                dbService.insertBookLoan(bookId, rollNo,issueDate,returnDate);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("IssuetBook done successfully");
            } else {
                System.err.println("Book record alread exists");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/html");
                response.getWriter().println("Book already exists");
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Selectbook = request.getParameter("selectbook"); 
        System.out.println("Issuebook of the student");

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        int rollNo = Integer.parseInt(request.getParameter("rollno"));
//        int price = Integer.parseInt(request.getParameter("price"));
//        String bookName = request.getParameter("bookname");
//        String department = request.getParameter("department");
//        String bookTitle = request.getParameter("booktitle");

        System.out.println("BookId:" + bookId);
        System.out.println("RollNo :" + rollNo);

        ResultSet rs = dbService.selectBookLoanByBookId(bookId);

        try {
            if (rs == null || !rs.next()) {
                System.out.println("Select book record");
               // dbService.selectBook(bookName);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("SelectBook done successfully");
            } else {
                System.err.println("Book record alread exists");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/html");
                response.getWriter().println("Book already exists");
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }

}