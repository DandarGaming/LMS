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
            System.out.println("Enter the numer for the option you wish to carry"
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
                    Loan.setBook();
                    break;
                case 2: 
                    System.out.println("Loading info on selected book...\n" + Loan.getBook());
                    break;
                case 3: 
                    System.out.println("Here are all the current books in the system:\n" + Book.GetAllBooks());
                    break;
                case 4:
                    Loan.Borrow();
                case 5: 
                    Loan.Return();
                    break;
                case 6: 
                    Loan.setMember();
                    break;
                case 7: 
                    //A function to view all members here
                    
                    
            }
            System.out.println("Would you like to exit the program? \n"
                    + "1. Yes, 2. No");
            userIntInput = scanner.nextInt();
            scanner.nextLine();
            if (userIntInput == 1) {
                exitProgram = true;
            }
            else {
                
            }

        } while (exitProgram == false);
        scanner.close();
    }


}
