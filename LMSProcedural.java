package com.mycompany.libraryprogram;

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class LMSProcedural {

    static String[] books = new String[100];
    static boolean[] borrowed = new boolean[100];
    static int bookCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addBook(input);
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    borrowBook(input);
                    break;
                case 4:
                    returnBook(input);
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    // Add Book
    public static void addBook(Scanner input) {
        System.out.print("Enter book title: ");
        String title = input.nextLine();

        books[bookCount] = title;
        borrowed[bookCount] = false;
        bookCount++;

        System.out.println("Book added successfully.");
    }

    // View Books
    public static void viewBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the system.");
            return;
        }

        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + books[i] +
                    (borrowed[i] ? " (Borrowed)" : " (Available)"));
        }
    }

    // Borrow Book
    public static void borrowBook(Scanner input) {
        viewBooks();
        System.out.print("Enter book number to borrow: ");
        int index = input.nextInt() - 1;

        if (index >= 0 && index < bookCount) {
            if (!borrowed[index]) {
                borrowed[index] = true;
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Return Book
    public static void returnBook(Scanner input) {
        viewBooks();
        System.out.print("Enter book number to return: ");
        int index = input.nextInt() - 1;

        if (index >= 0 && index < bookCount) {
            if (borrowed[index]) {
                borrowed[index] = false;
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}

