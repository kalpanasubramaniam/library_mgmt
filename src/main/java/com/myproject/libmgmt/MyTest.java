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
        c1.insertBookLoan(12,04,"2019-02-21","2019-02-25");
        c1.updateBookLoan set rollno=13,returndate="2019-06-25",fine=2 where bookid=14;
        
        
	  

	}

}



    


