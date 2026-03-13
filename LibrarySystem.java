package com.example;

import java.util.Scanner;

public class LibrarySystem {

    static Scanner scanner = new Scanner(System.in);
    static int userIntInput;
    static boolean exitProgram = false;
    static int totalBooks = 0;
    static String bookTitle;
    static String bookAuthor;
    static String bookIsbn;
    public static void main(String[] args) {
        System.out.println("Welcome");
        //Book.AddBook(1, "Hobbit", true, "Tolkien", "1234");
        do {
            System.out.println("Enter the numer of the option you wish to carry"
                    + " out"
                + "\n1. Add a Book: "
                + "\n2. View a Book: "
                + "\n3. View all Books"
                + "\n4. Add a new Member: "
                + "\n5. View all Members: "
            );
            userIntInput = scanner.nextInt();
            scanner.nextLine();
            switch (userIntInput) {
                case 1: 
                    Loan.setBook();
                case 2: 
                    System.out.println("Here are the details on your book" + Loan.getBook());
                case 3: 
                    System.out.println("Here are all the current books in the system" + Book.GetAllBooks());
                case 4: 
                    //Set Staff member function here
                case 5: 
                    //A function to view all members here
                    
            }
            System.out.println("Would you like to exit the program"
                    + "1. Yes, 2. No");
            if (userIntInput == 1) {
                exitProgram = true;
            }
            else {
                
            }

        } while (exitProgram == false);
    }


}