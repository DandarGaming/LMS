package com.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class Loan {

    private Member member;
    private Book[] books = new Book[10];
    
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
    private static final HashMap<String, Loan> loans = new HashMap<>();


    public Loan(Member member, Book book) {
        this.member = member;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    public Member getMember() {
        return this.member;
    }

    public static void setMember() {
        System.out.println("Enter the number of the member option you wish to add"
            + "\n1. Staff Member"
            + "\n2. Student Member"
        );
        userIntInput = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name of the -new member: ");
        memberName = scanner.nextLine();
        System.out.println("Enter the ID for this new member: ");
        memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new members email: ");
        memberEmail = scanner.nextLine();
        switch(userIntInput) {
            case 1:
                //Staff Member
                StaffMember newStaffMember = new StaffMember(memberName, memberId, memberEmail, 0);
                members.put(memberName, newStaffMember);
                break;
            case 2:
                //Student Member
                StudentMember newStudentMember = new StudentMember(memberName, memberId, memberEmail, 0);
                members.put(memberName, newStudentMember);
                break;   
        }
        System.out.println("You have successfully added the member " + members.get(memberName));
    }

    public static Book getBook() {
        System.out.println("Enter the name of the bookyou want to get: ");
        bookTitle = scanner.nextLine();
        return Book.GetBook(bookTitle);
    }

    public static void setBook() {
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
            if (member.getBorrowedBooks() < member.getMaxBooks()) {
                System.out.println("Enter the name of the book the member wishes to borrow: ");
                bookTitle = scanner.nextLine();
                Book book = Book.GetAllBooks().get(bookTitle);
                if (book == null){
                    System.out.println("Sorry but we dont have that book in our system");
                }
                else {
                    if (book.isAvailable() == true) {
                        book.setAvailable(false);
                        member.setBorrowedBooks(member.getBorrowedBooks() + 1);
                        Loan loan = loans.get(memberName);
                        if (loan == null) {
                            Loan newLoan = new Loan(member, book);
                            loans.put(memberName, newLoan);
                        }
                        else {         
                            for (int i = 0;  i < loan.books.length; i++) {
                                if (loan.books[i] == null) {
                                    loan.books[i] = book;
                                    break;
                                }
                            } 
                        }
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

    public static void Return() {
        System.out.println("Enter the name of the member: ");
        memberName = scanner.nextLine();
        Member member = members.get(memberName); 
        if (member == null) {
            System.out.println("That is not a member in our system");
        }
        else {
            if (member.getBorrowedBooks() == 0) {
                System.out.println("That member has not borrowed any books");
            }
            else {
                System.out.println("That member has borrowed " + member.getBorrowedBooks() + " books");
                System.out.println("Enter the name of the book the member wishes to return: ");
                bookTitle = scanner.nextLine();
                Book book = Book.GetAllBooks().get(bookTitle);
                if (book.isAvailable() == true){
                    System.out.println("Sorry but that book has not been borrowed");
                }
                else {
                    book.setAvailable(true);
                    Loan loan = loans.get(memberName);
                    for (int i = 0;  i < loan.books.length; i++) {
                        if (loan.books[i] == book) {
                            loan.books[i] = null;
                            break;
                        }
                    } 
                    member.setBorrowedBooks(member.getBorrowedBooks() - 1);
                    System.out.println(member.getName() + " has returnec " + book.getTitle() + " and now has " + member.getBorrowedBooks() + " borrowed books");                    
                }
            }
        }
    }

    public String getLoanDetails() {
        System.out.println("Enter the name of the person who you wish to check the loan details of");
        memberName = scanner.nextLine();
        Loan loan = loans.get(memberName);
        return Arrays.toString(loan.books);
    }

    @Override
    public String toString() {
        return "Member: " + member.getName() 
            + "\nBook: " + Arrays.toString(this.books);
    }
}
