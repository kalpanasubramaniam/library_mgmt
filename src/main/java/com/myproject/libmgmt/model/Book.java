package com.myproject.libmgmt.model;

public class Book {
	public int bookId;
	public String authorName;
	public int price;
	public String bookName;
	public String department;
	public String bookTitle;
	
   
    @Override
    public String toString() {
        return "BookId:" + bookId + "\n" +
                "AuthorName:" +authorName  + "\n" +
                "price:" + price + "\n" +
                "BookName:" + bookName + "\n" +
                "department   :" + department +"\n" +
                "BookTitle:" + bookTitle;


    }
}