package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class Member {

    static String name;
    static int memberId;
    static String email;
    static int borrowedBooks;

    static int userIntInput;
    static int totalBooks = 0;
    static String memberName;
    static int memberId;
    static String memberEmail;
    
    static Scanner scanner = new Scanner(System.in);

    private static final HashMap<String, Member> members = new HashMap<>();

    public Member(String name, int memberId, String email, int borrowedBooks) {
        Member.name = name;
        Member.memberId = memberId;
        Member.email = email;
        Member.borrowedBooks = borrowedBooks;
    }
    
    public static void getMember() {
        return this.member;
    }
            
    public String getName() {
        return Member.name;
    }

    public static void setMember() {
        //Outputs instructions on how to create a member and gets a user input results
        System.out.println("""
                           Enter the number of the member option you wish to add
                           1. Staff Member
                           2. Student Member""");
        userIntInput = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name of the -new member: ");
        memberName = scanner.nextLine();
        System.out.println("Enter the ID for this new member: ");
        memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new members email: ");
        memberEmail = scanner.nextLine();
        //checks the users input to call the right class's constructor
        switch(userIntInput) {
            case 1 -> {
                //Calls the StaffMember constructor
                StaffMember newStaffMember = new StaffMember(memberName, memberId, memberEmail, 0);
                //Adds the new StaffMember into the member hashmap under the name the user inputed
                members.put(memberName, newStaffMember);
            }
            case 2 -> {
                //Calls the StudentMember constructor
                StudentMember newStudentMember = new StudentMember(memberName, memberId, memberEmail, 0);
                //Adds the new StudentMember into the member hashmap under the name the user inputed
                members.put(memberName, newStudentMember);
            }   
        }
        //Outputs a message to alert the user it was successful
        System.out.println("You have successfully added the member " + members.get(memberName));
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
