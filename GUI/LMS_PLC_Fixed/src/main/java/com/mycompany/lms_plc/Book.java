package com.mycompany.lms_plc;

import java.util.HashMap;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    //Initalize a hashmap for Book objects
    private static final HashMap<String, Book> books = new HashMap<>();
    private static int bookCounter = 0;
    
    //A constructor method for Book objects
    public Book(int itemId, String title, boolean available, String author, String isbn) {
        //Calls the parent class to set the itemId, title and available
        super(itemId, title, available);
        //Sets the author
        this.author = author;
        //Sets the isbn
        this.isbn = isbn;
        //Gives conformation on book object being added
        System.out.println("Book title: " + title + " Author: " + author + " Availability: " + available + " Isbn: " + isbn + " Item ID: " + itemId);
    }

    //Gets the author of the a book
    public String getAuthor() {
        //checks if there are books in the system
        if (books.isEmpty()) {
            //Outputs if there are no books in the system
            return "Sorry but no books are in the system";
        }
        else {
            //Returns the author of the book object
            return this.author;
        }
    }

    //Sets the author for a book object
    public void updateAuthor(String newAuthor) {
        //Checks that there are books in the system
        if (books.isEmpty()) {
            //Outputs if there are no books in the system
            System.out.println("Sorry but no books are in the system");
        }
        else {
            //Sets the book object's author to the user input
            this.author = newAuthor;
        }
    }

    //Gets the isbn of a book
    public String getIsbn() {
        //Checks that there are books in the system
        if (books.isEmpty()) {
            //Outputs if there are no books in the system
            return "Sorry but no books are in the system";
        }
        else {
            //returns the isbn of the book object
            return this.isbn;
        }
    }

    //Sets the Isbn for a book object
    public void updateIsbn(String newIsbn) {
        //Checks that there are book objects
        if (books.isEmpty()) {
            //Outputs this if there is no book object
            System.out.println("Sorry but no books are in the system");
        }
        else {
            //Sets the book object's isbn to the user input
            this.isbn = newIsbn;
        }
       
    }

    //A overide method for the toSting method
    @Override
    public String toString() {
        return "[ Title: " + getTitle()
                + "  |  Author: " + author
                + "  |  ISBN: " + isbn
                + "  |  Available: " + isAvailable() + " ]";
    }

    //Adds new books to the books hashmap
    public static void AddBook(int itemId, String title, boolean available, String author, String isbn) {
        //Calls the Book constructor to make a new book object
        Book newbook = new Book(itemId, title, available, author, isbn);
        //adds the new book object to the books hashmap
        books.put(title, newbook);
        //Alerts the user that the book was added successfully 
        System.out.println("Your item has been successfully added: " + books.get(title));
    }

    //A function to get the entire books hashmap of book objects
    public static HashMap<String, Book> GetAllBooks() {
        //checks if the books hashmap has books in it
        if (books.isEmpty()) {
            System.out.println("Sorry but no books are in the system");
            return books;
        }
        else {
            return books;
        }
    }

    //Gets the book object as a string
    public static String GetBookString(String title) {
        //gets the book with the matching title from the books array
        Book book = books.get(title);
        //returns the book as a string
        return book.toString();
    }

    //Gets a book object
    public static Book GetBook(String title) {
        //Gets the book with the matcing title from the books array
        Book book = books.get(title);
        //checks if the book exists in the system
        if (book == null) {
            //outputs this if it does not exist
            System.out.println("We dont have that book in out system");
        }
        return book;
    }
    
    //Removes a book from the hashmap
    public static void RemoveBook(String title) {
        //Checks if there are books in the system and if there is that book in the system
        if (books.isEmpty()) {
            System.out.println("Sorry but there are no books in the system");
            
        } 
        else if (books.get(title) == null) {
            System.out.println("That book is not in our system");
        }
        else {
            books.remove(title);
        }
        
    }

    //GUI-friendly add. Returns "SUCCESS" or an error message.
    public static String removeBookGUI(String title) {
        if (!books.containsKey(title)) return "Book not found: " + title;
        books.remove(title);
        return "SUCCESS";
    }

    //GUI-friendly edit. Returns "SUCCESS" or an error message.
    public static String editBookGUI(String title, String newAuthor, String newIsbn) {
        Book book = books.get(title);
        if (book == null) return "Book not found: " + title;
        if (newAuthor != null && !newAuthor.isBlank()) book.updateAuthor(newAuthor);
        if (newIsbn != null && !newIsbn.isBlank()) book.updateIsbn(newIsbn);
        return "SUCCESS";
    }
    
    //GUI-friendly remove. Returns "SUCCESS" or an error message.
    public static String addBookGUI(String title, String author, String isbn) {
        if (title == null || title.isBlank()) return "Title cannot be empty";
        if (author == null || author.isBlank()) return "Author cannot be empty";
        if (isbn == null || isbn.isBlank()) return "ISBN cannot be empty";
        if (books.containsKey(title)) return "A book with that title already exists";
        bookCounter++;
        AddBook(bookCounter, title, true, author, isbn);
        return "SUCCESS";
    }
}
