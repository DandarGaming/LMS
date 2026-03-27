package com.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class Loan {

    private Member member;
    private Book[] books = new Book[10];
    
    static Scanner scanner = new Scanner(System.in);
    static int userIntInput;
    static int totalBooks = 0;
    static String bookTitle;
    static String bookAuthor;
    static String bookIsbn;
    static String memberName;
    static int memberId;
    static String memberEmail;

    //Initalize a hashamp for Member objects
    private static final HashMap<String, Member> members = new HashMap<>();
    //Initalize a hashmap for Loan objects
    private static final HashMap<String, Loan> loans = new HashMap<>();

    //creates the Loan object with the a member and book object
    public Loan(Member member, Book book) {
        this.member = member;
        //Puts the book the member is borrowing in the first available position
        //This should normally put the book in the first spot in the array
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }
    
    //A function to get the entire members hashmap of member objects
    public static HashMap<String, Member> GetAllMembers() {
        //checks if the members hashmap has members in it
        if (members.isEmpty()) {
            System.out.println("Sorry but no members are in the system");
            return members;
        }
        else {
            return members;
        }
    }

    //returns the member
    public Member getMember() {
        return this.member;
    }
    
    //Removes a member from the system
    public void removeMember(String memberName) {
        //Checks if there are members in the system and if that member is in the system
        if (members.isEmpty()) {
            System.out.println("Sorry but there are no members in the system");
            
        } 
        else if (members.get(memberName) == null) {
            System.out.println("That member is not in our system");
        }
        else {
            members.remove(memberName);
        }
    }
    
    //Creates a new member in the system
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
    
    //Gets the information needed to get a book from the Book class
    public static Book getBook() {
        System.out.println("Enter the name of the book you want to get: ");
        bookTitle = scanner.nextLine();
        //calls GetBook with the users input
        return Book.GetBook(bookTitle);
    }

    //Gets the information for a new book object and passes it to the book class
    public static void setBook() {
        System.out.println("Enter the title of the book: ");
        bookTitle = scanner.nextLine();
        System.out.println("Enter the name of the author: ");
        bookAuthor = scanner.nextLine();
        System.out.println("Enter the books Isbn: ");
        bookIsbn = scanner.nextLine();
        //updates the total book  by one
        totalBooks++;
        //calls the AddBook function in Book, to apply the variables
        Book.AddBook(totalBooks, bookTitle, true, bookAuthor, bookIsbn);
    }

    //Enables the user to borrow books from the system
    public static void Borrow() {
        System.out.println("Enter the name of the member: ");
        memberName = scanner.nextLine();
        //get the member object matching memberName
        Member member = members.get(memberName); 
        //checks whether the member is in the system
        if (member == null) {
            //outputs if the member is not in the system
            System.out.println("That is not a member in our system");
        }
        else {
            //checks if the member has already taken out the maximum amount of books
            if (member.getBorrowedBooks() < member.getMaxBooks()) {
                System.out.println("Enter the name of the book the member wishes to borrow: ");
                bookTitle = scanner.nextLine();
                //gets the book object with the matching title as bookTitle
                Book book = Book.GetAllBooks().get(bookTitle);
                //checks if the book is in out system
                if (book == null){
                    //outputs if the book is not in the system
                    System.out.println("Sorry but we dont have that book in our system");
                }
                else {
                    //checks if the book is currently available for borrowing
                    if (book.isAvailable() == true) {
                        //The availability gets set to false
                        book.setAvailable(false);
                        //The members borrowed books number is increased by 1
                        member.setBorrowedBooks(member.getBorrowedBooks() + 1);
                        //It gets the loan object that matches memberName
                        Loan loan = loans.get(memberName);
                        //checks if the member has taken out any loans before
                        if (loan == null) {
                            //If the member has not taken out any loans
                            //We create a new loan object with the book the requested and their member object
                            Loan newLoan = new Loan(member, book);
                            //The new loan object gets added to the loans hashmap
                            loans.put(memberName, newLoan);
                        }
                        else {    
                            //if the member has already taken out loans
                            //It loops through the array of the members loans until it finds the first free spot
                            //Once a free spot is found, it updates it with the new book
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
                        //outputs if the book is not currently available to borrow
                        System.out.println("That item is not available");
                        throw new IllegalStateException("Book is not available.");
                    }
                    
                }
            }
            else {
                //outputs if the borrow limit is at max
                System.out.println("That member has borrowed the maximum books and can no longer borrow a book until they return 1");
                throw new IllegalStateException("Borrow limit reached."); 
            }
        }
    }

    //Enables the user to return a book to the system
    public static void Return() {
        System.out.println("Enter the name of the member: ");
        memberName = scanner.nextLine();
        //gets the member object matching memberName
        Member member = members.get(memberName); 
        //Checks if the member exists
        if (member == null) {
            //prints if no member matches their input
            System.out.println("That is not a member in our system");
        }
        else {
            //checks if that member has borrowed books
            if (member.getBorrowedBooks() == 0) {
                //Outputs if member has borrowed no books
                System.out.println("That member has not borrowed any books");
            }
            else {
                //gets the loan object that matches memberName
                Loan loan = loans.get(memberName);
                //Tells the user what books the member has borrowed
                System.out.println("That member has borrowed " + member.getBorrowedBooks() + " books");
                System.out.println("The books they have borrowed are: " + Arrays.toString(loan.books));
                System.out.println("Enter the name of the book the member wishes to return: ");
                bookTitle = scanner.nextLine();
                //gets the book with the matching title
                Book book = Book.GetAllBooks().get(bookTitle);
                //Makes sure the book is actually borrowed
                if (book.isAvailable() == true){
                    //outputs if the book is not borrowed
                    System.out.println("Sorry but that book has not been borrowed");
                }
                else {
                    //updates books availability to true
                    book.setAvailable(true);
                    //loops through the members book array until it removes the matching book from their array
                    for (int i = 0;  i < loan.books.length; i++) {
                        if (loan.books[i] == book) {
                            loan.books[i] = null;
                            break;
                        }
                    } 
                    //Updates the borrowed books count to 1 less
                    member.setBorrowedBooks(member.getBorrowedBooks() - 1);
                    System.out.println(member.getName() + " has returned " + book.getTitle() + " and now has " + member.getBorrowedBooks() + " borrowed books");                    
                }
            }
        }
    }

    //Gets loan details and returns them
    public String getLoanDetails() {
        System.out.println("Enter the name of the person who you wish to check the loan details of");
        memberName = scanner.nextLine();
        //gets the loan object matching memberName
        Loan loan = loans.get(memberName);
        return Arrays.toString(loan.books);
    }

    //Overrides the base toString method
    @Override
    public String toString() {
        return "Member: " + member.getName() 
            + "\nBook: " + Arrays.toString(this.books);
    }
}
