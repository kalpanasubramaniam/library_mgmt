package com.myproject.libmgmt.model;

public class Book {
	public int bookId;
	public String bookName;
	public String authorName;
	public String department;
	public String bookTitle;
	public int price;

	public Book(int bookId, String authorName, int price, String bookName, String department, String bookTitle) {
		this.bookId = bookId;
		this.authorName = authorName;
		this.price = price;
		this.bookName = bookName;
		this.department = department;
		this.bookTitle = bookTitle;
	}

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