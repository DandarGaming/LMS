package com.mycompany.lms_plc;

/**
 *
 * @author DanielSukhanov
 */
public class LMS_Class {
    public class LibraryItem {

    private int itemId;
    private String title;
    private boolean available;

    public LibraryItem(int itemId, String title, boolean available) {
        // constructor implementation
    }

    public int getItemId() {
        // getter implementation
        return 0;
    }

    public void setItemId(int itemId) {
        // setter implementation
    }

    public String getTitle() {
        // getter implementation
        return null;
    }

    public void setTitle(String title) {
        // setter implementation
    }

    public boolean isAvailable() {
        // getter implementation
        return false;
    }

    public void setAvailable(boolean available) {
        // setter implementation
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
    public class Book extends LibraryItem {

    private String author;
    private String isbn;

    public Book(int itemId, String title, boolean available, String author, String isbn) {
        super(itemId, title, available);
        // constructor implementation
    }

    public String getAuthor() {
        // getter implementation
        return null;
    }

    public void setAuthor(String author) {
        // setter implementation
    }

    public String getIsbn() {
        // getter implementation
        return null;
    }

    public void setIsbn(String isbn) {
        // setter implementation
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
    public class Member {

    private String name;
    private int memberId;
    private String email;
    private int borrowedBooks;

    public Member(String name, int memberId, String email, int borrowedBooks) {
        // constructor implementation
    }

    public String getName() {
        // getter implementation
        return null;
    }

    public void setName(String name) {
        // setter implementation
    }

    public int getMemberId() {
        // getter implementation
        return 0;
    }

    public void setMemberId(int memberId) {
        // setter implementation
    }

    public String getEmail() {
        // getter implementation
        return null;
    }

    public void setEmail(String email) {
        // setter implementation
    }

    public int getBorrowedBooks() {
        // getter implementation
        return 0;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        // setter implementation
    }

    public int calculateBorrowLimit() {
        // method implementation
        return 0;
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
    public class StudentMember extends Member {

    private static final int MAX_BOOKS = 5;

    public StudentMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
        // constructor implementation
    }

    public int getMaxBooks() {
        // getter implementation
        return 0;
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
    public class StaffMember extends Member {

    private static final int MAX_BOOKS = 10;

    public StaffMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
        // constructor implementation
    }

    public int getMaxBooks() {
        // getter implementation
        return 0;
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
    public class Loan {

    private Member member;
    private Book book;

    public Loan(Member member, Book book) {
        // constructor implementation
    }

    public Member getMember() {
        // getter implementation
        return null;
    }

    public void setMember(Member member) {
        // setter implementation
    }

    public Book getBook() {
        // getter implementation
        return null;
    }

    public void setBook(Book book) {
        // setter implementation
    }

    public String getLoanDetails() {
        // method implementation
        return null;
    }

    @Override
    public String toString() {
        // toString implementation
        return null;
    }
}
}

