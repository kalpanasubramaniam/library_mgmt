package com.myproject.libmgmt.model;

public class Student {
    public int rollNo;
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public String email;

    public Student(){
        super();
    }

    public Student(int rollNo, String firstName, String lastName, String userName, String password, String email) {
        super();
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    @Override
    public String toString() {
        return "FirstName:" + firstName + "\n" +
                "LastName:" + lastName + "\n" +
                "UserName:" + userName + "\n" +
                "Password:" + password + "\n" +
                "Email   :" + email;


    }
}
