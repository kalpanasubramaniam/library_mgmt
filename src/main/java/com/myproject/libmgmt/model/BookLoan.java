package com.myproject.libmgmt.model;

public class BookLoan {
    public int bookId;
    public int rollNo;
    public String issueDate;
    public String returnDate;
    public String dueDate;
    public int fine;

    public int calculateFine() throws Exception {
//        if(dueDate == null) {
//            throw new Exception("No due date found");
//        }
        return 10;
    }

}
