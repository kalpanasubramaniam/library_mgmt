package com.myproject.libmgmt;

import com.myproject.libmgmt.db.BookDBService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class BookServlet extends HttpServlet {

    BookDBService dbService = new BookDBService();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("InsertBook of the student");

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        String authorName = request.getParameter("authorname");
        int price = Integer.parseInt(request.getParameter("price"));
        String bookName = request.getParameter("bookname");
        String department = request.getParameter("department");
        String bookTitle = request.getParameter("booktitle");

        System.out.println("BookId:" + bookId);
        System.out.println("AuthorName :" + authorName);

        ResultSet rs = dbService.selectBookByBookId(bookId);

        try {
            if (rs == null || !rs.next()) {
                System.out.println("Inserting book record");
                dbService.insertBook(bookId, authorName, price, bookName, department, bookTitle);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("InsertBook done successfully");
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
        System.out.println("Selectbook of the student");

        int bookId = Integer.parseInt(request.getParameter("bookid"));
        String authorName = request.getParameter("authorname");
        int price = Integer.parseInt(request.getParameter("price"));
        String bookName = request.getParameter("bookname");
        String department = request.getParameter("department");
        String bookTitle = request.getParameter("booktitle");

        System.out.println("BookId:" + bookId);
        System.out.println("AuthorName :" + authorName);

        ResultSet rs = dbService.selectBookByBookId(bookId);

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