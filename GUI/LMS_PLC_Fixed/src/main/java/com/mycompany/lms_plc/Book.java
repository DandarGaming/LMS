package com.mycompany.lms_plc;

import java.util.HashMap;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    private static final HashMap<String, Book> books = new HashMap<>();
    private static int bookCounter = 0;

    public Book(int itemId, String title, boolean available, String author, String isbn) {
        super(itemId, title, available);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() { return this.author; }
    public void updateAuthor(String newAuthor) { this.author = newAuthor; }

    public String getIsbn() { return this.isbn; }
    public void updateIsbn(String newIsbn) { this.isbn = newIsbn; }

    @Override
    public String toString() {
        return "[ Title: " + getTitle()
                + "  |  Author: " + author
                + "  |  ISBN: " + isbn
                + "  |  Available: " + isAvailable() + " ]";//outputs title,author,isbn and if the book is available
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    public static void AddBook(int itemId, String title, boolean available, String author, String isbn) {
        books.put(title, new Book(itemId, title, available, author, isbn));
    }

    /**
     * GUI-friendly add. Returns "SUCCESS" or an error message.
     */
    public static String addBookGUI(String title, String author, String isbn) {
        if (title == null || title.isBlank()) return "Title cannot be empty";
        if (author == null || author.isBlank()) return "Author cannot be empty";
        if (isbn == null || isbn.isBlank()) return "ISBN cannot be empty";
        if (books.containsKey(title)) return "A book with that title already exists";//error handling if it is null or it already exists
        bookCounter++; 
        AddBook(bookCounter, title, true, author, isbn);
        return "SUCCESS";
    }

    /**
     * GUI-friendly edit. Returns "SUCCESS" or an error message.
     */
    public static String editBookGUI(String title, String newAuthor, String newIsbn) {
        Book book = books.get(title);
        if (book == null) return "Book not found: " + title;
        if (newAuthor != null && !newAuthor.isBlank()) book.updateAuthor(newAuthor);
        if (newIsbn != null && !newIsbn.isBlank()) book.updateIsbn(newIsbn);
        return "SUCCESS";
    }

    public static HashMap<String, Book> GetAllBooks() { return books; }

    public static Book GetBook(String title) { return books.get(title); }

    public static String GetBookString(String title) {
        Book book = books.get(title);
        return book == null ? "Book not found" : book.toString();
    }

    /**
     * GUI-friendly remove. Returns "SUCCESS" or an error message.
     */
    public static String removeBookGUI(String title) {
        if (!books.containsKey(title)) return "Book not found: " + title;
        books.remove(title);
        return "SUCCESS";
    }
}
