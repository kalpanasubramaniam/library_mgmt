package com.myproject.libmgmt;

import com.myproject.libmgmt.db.*;

public class MyTest{
	 public static void main(String[] args) {
		System.out.println("myproject");
		BookDBService b = new BookDBService();
		b.createBookTable();
		BookDBService b1 = new BookDBService();
		b1.insertBook(124 ,"BalaGurusamy", 150,"Programming in Java", "CSE", "Java");
        BookLoanDBService c = new BookLoanDBService();
        c.createBookLoanTable();
        BookLoanDBService c1 = new BookLoanDBService();
        c1.insertBookLoan(125,003,21-2-19,25-2-19,26-2-19);
	  

	}

}



    


