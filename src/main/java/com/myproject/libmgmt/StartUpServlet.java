package com.myproject.libmgmt;

import javax.servlet.http.HttpServlet;

import com.myproject.libmgmt.db.*;

public class StartUpServlet extends HttpServlet{

public void init() {
    System.out.println( getServletName() + ": init start" );

    StudentDBService dbService = new StudentDBService();
    dbService.createStudeTable();

    System.out.println( getServletName() + ": initialised" );

  }
}