package com.mycompany.lms_plc;

import java.util.Scanner;

public class LibrarySystem {

    static Scanner scanner = new Scanner(System.in);
    static int userIntInput;
    static boolean exitProgram = false;
    static int totalBooks = 0;
    static String bookTitle;
    static String bookAuthor;
    static String bookIsbn;
    static String memberName;
    static String memberEmail;
    static String memberLevel;
    static int memberID;
    static Member member;
    
    public static void main(String[] args) {
        System.out.println("Welcome");
        //Book.AddBook(1, "Hobbit", true, "Tolkien", "1234");
        do {
            System.out.println("Enter the number for the option you wish to carry"
                    + " out:"
                + "\n1. Add a Book"
                + "\n2. View a Book"
                + "\n3. View all Books"
                + "\n4. Take out a book"
                + "\n5. Return a Book"
                + "\n6. Add a new Member"
                + "\n7. View all Members"
            );
            userIntInput = scanner.nextInt();
            scanner.nextLine();
            switch (userIntInput) {
                case 1: 
                    int bookId;
                    System.out.println("Enter the Id of the book: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the title of the book: ");
                    bookTitle = scanner.nextLine();
                    System.out.println("Enter the name of the books author: ");
                    bookAuthor = scanner.nextLine();
                    System.out.println("Enter the book Isbn: ");
                    bookIsbn = scanner.nextLine();
                    Book.AddBook(bookId, bookTitle, true, bookAuthor, bookIsbn);
                    break;
                case 2: 
                    System.out.println("Enter the name of the book you want to get: ");
                    bookTitle = scanner.nextLine();
                    System.out.println("Loading info on selected book...\n" + Book.GetBook(bookTitle));
                    break;
                case 3: 
                    System.out.println("Here are all the current books in the system:\n" + Book.GetAllBooks());
                    break;
                case 4:
                    System.out.println("Enter the name of the member: ");
                    memberName = scanner.nextLine();
                    //get the member object matching memberName
                    member = Loan.getMember(memberName); 
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
                                    Loan.borrowGUI(memberName, bookTitle);
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
                    break;
                case 5: 
                    System.out.println("Enter the name of the member: ");
                    memberName = scanner.nextLine();
                    //gets the member object matching memberName
                    member = Loan.getMember(memberName); 
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
                            String loan  = Loan.getMemberLoansGUI(memberName);
                            //Tells the user what books the member has borrowed
                            System.out.println("That member has borrowed " + member.getBorrowedBooks() + " books");
                            System.out.println("The books they have borrowed are: " + loan);
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
                                Loan.returnGUI(memberName, bookTitle);
                                System.out.println(member.getName() + " has returned " + book.getTitle() + " and now has " + member.getBorrowedBooks() + " borrowed books");                    
                            }
                        }
                    }
                    
                    break;
                case 6: 
                    //Outputs instructions on how to create a member and gets a user input results
                    do {
                        System.out.println("""
                                           Enter the number of the member option you wish to add
                                           1. Staff Member
                                           2. Student Member""");
                        userIntInput = scanner.nextInt();
                        scanner.nextLine();
                        switch (userIntInput) {
                            case 1 -> memberLevel = "Staff";
                            case 2 -> memberLevel = "Student";
                            default -> System.out.println("Not a valid input. \n Enter a valid input");
                        }
                    } while (userIntInput == 1 && userIntInput == 2);   
                    System.out.println("Enter the name of the -new member: ");
                    memberName = scanner.nextLine();
                    System.out.println("Enter a number ID for this new member: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the new members email: ");
                    memberEmail = scanner.nextLine();   
                    String results = Loan.addMemberGUI(memberLevel, memberName, memberID, memberEmail); 
                    System.out.println(results);
                    //Outputs a message to alert the user it was successful
                    System.out.println("You have successfully added the member " + Loan.getMember(memberName));
                    break;

                case 7: 
                    //A function to view all members here
                    System.out.println(Loan.GetAllMembers());
                    
                    
            }
            userIntInput = 0;
            
            do {
                System.out.println("Would you like to exit the program? \n"
                    + "1. Yes, 2. No");
                userIntInput = scanner.nextInt();
                scanner.nextLine();
                switch (userIntInput) {
                    case 1:
                        exitProgram = true;
                        break;
                    case 2:
                        exitProgram = false;
                        break;
                    default:
                        System.out.println("Enter Valid Input");
                        userIntInput = 0;
                        break;
                }
            } while (userIntInput != 1 && userIntInput != 2);
                
            

        } while (exitProgram == false);
        scanner.close();
    }


}
