package com.myproject.libmgmt.model;

public class OverDueBook {
    public int bookId;
    public String bookName;
    public int rollNo;
    public String firstName;
    public String lastName;
    public String issueDate;
    public String dueDate;

    public OverDueBook(int bookId, String bookName, int rollNo, String firstName, String lastName, String issueDate, String dueDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }
}
