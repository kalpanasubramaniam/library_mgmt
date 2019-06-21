package com.myproject.libmgmt;

import com.myproject.libmgmt.db.*;

public class MyTest{
	 public static void main(String[] args) {
		System.out.println("myproject");
		BookDBService b = new BookDBService();
		b.createBookTable();
		BookDBService b1 = new BookDBService();
		b1.insertBook(124 ,"BalaGurusamy", 150,"Programming in Java", "CSE", "Java");
        
	  

	}

}



    


