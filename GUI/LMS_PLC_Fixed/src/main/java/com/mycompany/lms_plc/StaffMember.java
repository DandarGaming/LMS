package com.mycompany.lms_plc;

public class StaffMember extends Member {

    private final int maxBooks = 10; //sets maxBooks to 10 for staff Member

    public StaffMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    @Override
    public int getMaxBooks() { return maxBooks; }

    @Override
    public int calculateBorrowLimit() { return maxBooks; }

    @Override
    public String toString() { return "[Staff] " + super.toString(); }
}
