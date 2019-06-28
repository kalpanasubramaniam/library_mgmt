package com.myproject.libmgmt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myproject.libmgmt.db.BookDBService;
import com.myproject.libmgmt.model.Book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookServlet extends HttpServlet {

    BookDBService dbService = new BookDBService();
    Gson gson = new Gson();


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

        Book book = dbService.selectBookByBookId(bookId);

        try {
            if (book == null) {
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
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Search Book");
        int bookId = -1;
        String bookName =  null;
        String  searchBook = null;

        List<Book> searchResult = new ArrayList<Book>();

        try {
            String bookIdRequest = request.getParameter("bookid");
            System.out.println("Book Id:"+bookIdRequest);
            System.out.println("Book Id:"+bookIdRequest);
            if(bookIdRequest != null && !(bookIdRequest.trim().isEmpty())){
                bookId = Integer.parseInt(bookIdRequest);
            }

            bookName = request.getParameter("bookname");
            searchBook  = request.getParameter("searchbook");

            System.out.println("BookId:" + bookId);
            System.out.println("AuthorName :" + bookName);
            System.out.println("Search Book :" + searchBook);


            if(bookId != -1) {
                Book book = dbService.selectBookByBookId(bookId);
                if(book != null) searchResult.add(book);

            } else if( bookName != null && bookName.trim().length() > 0) {
                searchResult.addAll(dbService.searchBookByBookName(bookName));
            } else {
                throw new Exception("Invalid input");
            }

            System.out.println("No of books found:"+ searchResult.size());
                Type collectionType = new TypeToken<List<Book>>(){}.getType();
                String searchResultJsonString = gson.toJson(searchResult, collectionType);

            System.out.println(searchResultJsonString);

            System.out.println("Select book record");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/json");
            response.getWriter().println(searchResultJsonString);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }

}