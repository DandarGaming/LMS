package com.example;

import static com.example.Loan.memberEmail;
import static com.example.Loan.memberId;
import static com.example.Loan.memberName;
import static com.example.Loan.scanner;
import static com.example.Loan.userIntInput;
import java.util.HashMap;

public class Member {

    private String name;
    private int memberId;
    private String email;
    private int borrowedBooks;
    
    static String newMemberName;
    static int newMemberId;
    static String newMemberEmail;
    
     //Initalize a hashamp for Member objects
    private static final HashMap<String, Member> members = new HashMap<>();

    public Member(String name, int memberId, String email, int borrowedBooks) {
        this.name = name;
        this.memberId = memberId;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
    }

    public String getName() {
        return this.name;
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
        newMemberName = scanner.nextLine();
        System.out.println("Enter the ID for this new member: ");
        newMemberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new members email: ");
        newMemberEmail = scanner.nextLine();
        //checks the users input to call the right class's constructor
        switch(userIntInput) {
            case 1 -> {
                //Calls the StaffMember constructor
                StaffMember newStaffMember = new StaffMember(newMemberName, newMemberId, newMemberEmail, 0);
                //Adds the new StaffMember into the member hashmap under the name the user inputed
                members.put(memberName, newStaffMember);
            }
            case 2 -> {
                //Calls the StudentMember constructor
                StudentMember newStudentMember = new StudentMember(newMemberName, newMemberId, newMemberEmail, 0);
                //Adds the new StudentMember into the member hashmap under the name the user inputed
                members.put(memberName, newStudentMember);
            }   
        }
        //Outputs a message to alert the user it was successful
        System.out.println("You have successfully added the member " + members.get(memberName));
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
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
