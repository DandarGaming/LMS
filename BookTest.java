package com.mycompany.lms;

/**
 *
 * @author Student Name
 */
public class BookTest {
    
    
    public static void main(String[] args) {
        System.out.println("=== Book Test ===");

        // Create a Book instance  BOO1, Java Programming, J. Smith, 12345
        Book.AddBook(001, "Java Programming", true, "J. Smith", "12345");
        Book book1 = Book.GetBook("Java Programming");

        // Print initial state
        System.out.println("Initial Book: " + book1);

        // Borrow the book
        System.out.println("\nBorrowing the book...");
        if (book1.isAvailable()) {
            book1.setAvailable(false);

            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is not available to borrow.");
        }

        // Attempt to borrow again (should fail)
        System.out.println("\nAttempting to borrow again...");
        if (book1.isAvailable()) {
            book1.setAvailable(false);
            System.out.println("Book is not available to borrow");
        }
        //Insert code to check if book is not available
        //Output "Book is already on loan." 
        else {
            System.out.println("Book is already on loan");
        }


        // Return the book
        System.out.println("\nReturning the book...");
        //Set book availabilty to true 
        book1.setAvailable(true);
        System.out.println("Book returned successfully.");

        // Final state
        System.out.println("\nFinal Book: " + book1);
    }
}
