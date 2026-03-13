package com.example;

import java.util.Scanner;
import java.util.HashMap;

public class Loan {

    private Member member;
    private Book book;
    
    static Scanner scanner = new Scanner(System.in);
    static int userIntInput;
    static boolean exitProgram = false;
    static int totalBooks = 0;
    static String bookTitle;
    static String bookAuthor;
    static String bookIsbn;
    static String memberName;
    static int memberId;
    static String memberEmail;

    private static final HashMap<String, Member> members = new HashMap<>();


    public Loan(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember() {
        System.out.println("Enter the number of the member option you wish to add"
            + "\n1. Staff Member"
            + "\n2. Student Member"
        );
        userIntInput = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name of the new member: ");
        memberName = scanner.nextLine();
        System.out.println("Enter the ID for this new member: ");
        memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new members email: ");
        memberEmail = scanner.nextLine();
        switch(userIntInput) {
            case 1:
                //Staff Member
                StaffMember newStaffMember = new StaffMember(memberName, memberId, memberEmail, memberId);
                members.put(memberName, newStaffMember);
                break;
            case 2:
                //Student Member
                StudentMember newStudentMember = new StudentMember(memberName, memberId, memberEmail, memberId);
                members.put(memberName, newStudentMember);
                break;   
        }
        System.out.println("You have successfully added the member " + members.get(memberName));
    }

    public void setBook() {
        System.out.println("Enter the title of the book: ");
        bookTitle = scanner.nextLine();
        System.out.println("Enter the name of the author: ");
        bookAuthor = scanner.nextLine();
        System.out.println("Enter the books Isbn: ");
        bookIsbn = scanner.nextLine();
        totalBooks++;
        Book.AddBook(totalBooks, bookTitle, true, bookAuthor, bookIsbn);
    }

    public static void Borrow() {
        System.out.println("Enter the name of the member: ");
        memberName = scanner.nextLine();
        Member member = members.get(memberName); 
        if (member == null) {
            System.out.println("That is not a member in our system");
        }
        else {
            if (member.borrowedBooks < member.maxBooks) {
                System.out.println("Enter the name of the book the member wishes to borrow: ");
                bookTitle = scanner.nextLine();
                Book book = Book.GetAllBooks().get(bookTitle);
                if (book == null){
                    System.out.println("Sorry but we dont have that book in our system");
                }
                else {
                    if (LibraryItem.isAvailable(); == true) {
                        LibraryItem.setAvailable(false);
                        member.borrowedBooks++;
                        System.out.println(member.getName() + " has borrowed " + book.getTitle() + " and now has " + member.getBorrowedBooks() + " borrowed books");
                    }
                    else {
                        System.out.println("That item is not available");
                    }
                    
                }
            }
            else {
                System.out.println("That member has borrowed the maximum books and can no longer borrow a book until they return 1");
            }
        }
    }

    // public String getLoanDetails() {
    //     System.out.println("Enter the name of the person who you wish to check the loan details of");
    //     memberName = scanner.nextLine();
    //     return Member.getBorrowedBooks();
    // }

    // @Override
    // public String toString() {
    // Does this file need a toString??? It stores no constant information on anything
    // }
}
