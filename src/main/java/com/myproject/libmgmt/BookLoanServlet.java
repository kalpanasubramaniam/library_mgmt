package com.myproject.libmgmt;

import com.myproject.libmgmt.db.BookLoanDBService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class BookLoanServlet extends HttpServlet {

    BookLoanDBService dbService = new BookLoanDBService();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("InsertBook of the student");

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        int rollNo = Integer.parseInt(request.getParameter("rollno"));
        int issuedate = Integer.parseInt(request.getParameter("issuedate"));
        int duedate = Integer.parseInt(request.getParameter("duedate"));
        int returndate = Integer.parseInt(request.getParameter("returndate"));
        

        System.out.println("BookId:" + bookId);
        System.out.println("RollNo :" + rollNo);

        ResultSet rs = dbService.selectBookLoanByBookId(bookId);

        try {
            if (rs == null || !rs.next()) {
                System.out.println("Issue book record");
                dbService.insertBookLoan(bookId, rollNo, issuedate, duedate, returndate);
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