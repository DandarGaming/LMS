package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class Member {

    static String name;
    static int memberId;
    static String email;
    static int borrowedBooks;
    
    static Scanner scanner = new Scanner(System.in);

    public Member(String name, int memberId, String email, int borrowedBooks) {
        Member.name = name;
        Member.memberId = memberId;
        Member.email = email;
        Member.borrowedBooks = borrowedBooks;
    }
            
    public String getName() {
        return Member.name;
    }

    public void setName(String name) {
        Member.name = name;
    }

    public int getMemberId() {
        return Member.memberId;
    }

    public void setMemberId(int memberId) {
        Member.memberId = memberId;
    }

    public String getEmail() {
        return Member.email;
    }

    public void setEmail(String email) {
        Member.email = email;
    }

    public int getBorrowedBooks() {
        return Member.borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        Member.borrowedBooks = borrowedBooks;
    }

    public int calculateBorrowLimit() {
        return 0; // default output, will be overrided by StaffMember and StudentMember classes
    }

    public int getMaxBooks() {
        return 0;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nMember ID: " + memberId +
                "\nEmail: " + this.email +
                "\nBorrowed Books: " + borrowedBooks;
    }
}
