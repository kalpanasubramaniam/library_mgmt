package com.myproject.libmgmt;

import javax.servlet.http.HttpServlet;

import com.myproject.libmgmt.db.*;

public class StartUpServlet extends HttpServlet{

public void init() {
    System.out.println( getServletName() + ": init start" );

    StudentDBService dbService = new StudentDBService();
    dbService.createStudeTable();

    BookLoanDBService bookLoanDBService = new BookLoanDBService();
    bookLoanDBService.createBookLoanTable();

    BookDBService bookDBService = new BookDBService();
    bookDBService.createBookTable();

    System.out.println( getServletName() + ": initialised" );

  }
}