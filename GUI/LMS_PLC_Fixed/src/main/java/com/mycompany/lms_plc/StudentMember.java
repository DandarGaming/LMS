package com.mycompany.lms_plc;

public class StudentMember extends Member {

    private final int maxBooks = 5;

    public StudentMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    @Override
    public int getMaxBooks() { return maxBooks; }

    @Override
    public int calculateBorrowLimit() { return maxBooks; }

    @Override
    public String toString() { return "[Student] " + super.toString(); }
}
