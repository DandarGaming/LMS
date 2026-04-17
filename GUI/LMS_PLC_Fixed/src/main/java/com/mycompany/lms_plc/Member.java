package com.mycompany.lms_plc;

public class Member {

    private String name;
    private int memberId;
    private String email;
    private int borrowedBooks;

    public Member(String name, int memberId, String email, int borrowedBooks) {
        this.name = name;
        this.memberId = memberId;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getMemberId() { return this.memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public int getBorrowedBooks() { return this.borrowedBooks; }
    public void setBorrowedBooks(int borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    public int calculateBorrowLimit() { return 0; }
    public int getMaxBooks() { return 0; }

    @Override
    public String toString() {
        return "Name: " + name //Output of name of a member
                + "\nMember ID: " + memberId //Output of member ID of a member
                + "\nEmail: " + email //Output of Email of a member
                + "\nBorrowed Books: " + borrowedBooks //Output of borrowed books of a member
                + "\nMax Books: " + getMaxBooks(); //Output max Books based on Member
    }
}
