package com.example;

import java.util.HashMap;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    //Initalize a hashmap for Book objects
    private static final HashMap<String, Book> books = new HashMap<>();
    
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
    public void setAuthor() {
        //Checks that there are books in the system
        if (books.isEmpty()) {
            //Outputs if there are no books in the system
            System.out.println("Sorry but no books are in the system");
        }
        else {
            //Gets an input from the user for the author
            System.out.println("Enter the name of the author for this book");
            String newAuthor = scanner.nextLine();
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
    public void setIsbn() {
        //Checks that there are book objects
        if (books.isEmpty()) {
            //Outputs this if there is no book object
            System.out.println("Sorry but no books are in the system");
        }
        else {
            //Gets an input from the user for the Isbn
            System.out.println("Enter the Isbn for this book");
            String newIsbn = scanner.nextLine();
            //Sets the book object's isbn to the user input
            this.isbn = newIsbn;
        }
       
    }

    //A overide method for the toSting method
    @Override
    public String toString() {
        return  " [ Title = " + getTitle() + "\n" +
                "Author = " + this.author + "\n" +
                "Isbn = " + this.isbn + "\n" +
                "Available = " + isAvailable() + " ]\n";
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
    
    public static void RemoveBook(String title) {
        books.remove(title);
    }
}
