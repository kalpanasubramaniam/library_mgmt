package com.myproject.libmgmt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myproject.libmgmt.db.BookLoanDBService;
import com.myproject.libmgmt.model.Book;
import com.myproject.libmgmt.model.BookLoan;
import com.myproject.libmgmt.model.OverDueBook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class BookLoanServlet extends HttpServlet {

    BookLoanDBService dbService = new BookLoanDBService();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Overdue Books");
        try{
            String overduebooks = request.getParameter("overduebooks");
            System.out.println(overduebooks);
            if(overduebooks != "true"){
                Date currentDate = new Date();
                String overDueDate = sdf.format(currentDate);
                List<OverDueBook> overDueBooks = dbService.getOverDueBooks(overDueDate);

                System.out.println("No of overdue books found:"+ overDueBooks.size());
                Type collectionType = new TypeToken<List<OverDueBook>>(){}.getType();
                String searchResultJsonString = gson.toJson(overDueBooks, collectionType);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/json");
                response.getWriter().println(searchResultJsonString);
            } else {
                throw new Exception("Invalid Parameter");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("IssueBook of the student");
        try {
            int bookId = Integer.parseInt(request.getParameter("bookid"));
            int rollNo = Integer.parseInt(request.getParameter("rollno"));

            Date currentDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, 14);
            Date dueDate = c.getTime();
            String issueDate = sdf.format(currentDate);
            String returnDate = sdf.format(dueDate);

            System.out.println(sdf.format(dueDate));


            System.out.println("BookId:" + bookId);
            System.out.println("RollNo :" + rollNo);

            BookLoan bookLoan = dbService.selectBookLoanByBookId(bookId);

            System.out.println("Result Set" + bookLoan);

            if (bookLoan == null) {
                System.out.println("Book is not isssued to anyone");
                dbService.insertBookLoan(bookId, rollNo, issueDate, returnDate);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("IssuetBook done successfully");
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

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Return the book of the student");
        try {
            int bookId = Integer.parseInt(request.getParameter("bookid"));
            int rollNo = Integer.parseInt(request.getParameter("rollno"));

            Date currentDate = new Date();

            String returnDate = sdf.format(currentDate);
            System.out.println("Return Date:"+returnDate);


            System.out.println("BookId:" + bookId);
            System.out.println("RollNo :" + rollNo);

            BookLoan bookLoan = dbService.selectBookLoanByBookId(bookId);

            if(bookLoan == null || bookLoan.rollNo != rollNo){
                String errorMsg = "Book is not issued to this student with roll number" + rollNo;
                System.err.println(errorMsg);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/html");
                response.getWriter().println(errorMsg);
            } {
                bookLoan.returnDate = returnDate;
                bookLoan.fine = bookLoan.calculateFine();
                dbService.updateBookLoan(bookLoan);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html");
                response.getWriter().println("Book returned successfully. Due Date:"+bookLoan.dueDate+ "Fine Amount:"+ bookLoan.fine);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Internal Server error");
        }
    }

}