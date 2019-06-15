package com.myproject.libmgmt.model;

public class Student {
    public int rollNo;
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public String email;


    @Override
    public String toString() {
        return "FirstName:" + firstName + "\n" +
                "LastName:" + lastName + "\n" +
                "UserName:" + userName + "\n" +
                "Password:" + password + "\n" +
                "Email   :" + email;


    }
}
