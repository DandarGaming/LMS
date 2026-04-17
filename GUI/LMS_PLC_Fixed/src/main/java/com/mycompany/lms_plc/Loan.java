package com.mycompany.lms_plc;

import java.util.Arrays;
import java.util.HashMap;

public class Loan {

    private Member member;
    private Book[] books = new Book[10];

    private static final HashMap<String, Member> members = new HashMap<>();
    private static final HashMap<String, Loan> loans = new HashMap<>();

    public Loan(Member member, Book book) {
        this.member = member;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) { books[i] = book; break; }
        }
    }

    // ── Member management ─────────────────────────────────────────────────────

    public static HashMap<String, Member> GetAllMembers() { return members; }

    public Member getMember() { return this.member; }

    /**
     * GUI-friendly member addition.
     * @param type "Student" or "Staff"
     * @return "SUCCESS" or an error message
     */
    public static String addMemberGUI(String type, String name, int memberId, String email) {
        if (name == null || name.isBlank()) return "Name cannot be empty";
        if (members.containsKey(name)) return "A member with that name already exists"; //error handling in case name is empty or it already exists
        switch (type) {
            case "Student" -> members.put(name, new StudentMember(name, memberId, email, 0));
            case "Staff"   -> members.put(name, new StaffMember(name, memberId, email, 0));
            default        -> { return "Invalid member type: " + type; }//error handling
        }
        return "SUCCESS";
    }

    /**
     * GUI-friendly member removal.
     * @return "SUCCESS" or an error message
     */
    public static String removeMemberGUI(String name) {
        if (!members.containsKey(name)) return "Member not found: " + name; //error handling
        members.remove(name);
        loans.remove(name);
        return "SUCCESS";
    }

    /**
     * GUI-friendly member info retrieval.
     */
    public static String getMemberInfoGUI(String name) {
        Member m = members.get(name);
        return m == null ? "Member not found: " + name : m.toString();//error handling member not in system
    }

    // ── Loan management ───────────────────────────────────────────────────────

    /**
     * GUI-friendly borrow.
     * @return "SUCCESS" or an error message
     */
    public static String borrowGUI(String memberName, String bookTitle) {
        Member member = members.get(memberName);
        if (member == null) return "Member not found: " + memberName;//output if member not in system
        if (member.getBorrowedBooks() >= member.getMaxBooks())
            return memberName + " has reached the borrow limit (" + member.getMaxBooks() + " books)";//book limit

        Book book = Book.GetAllBooks().get(bookTitle);
        if (book == null) return "Book not found: " + bookTitle;//error handling book not in system
        if (!book.isAvailable()) return "\"" + bookTitle + "\" is not currently available";// outputs if book is not available

        book.setAvailable(false);
        member.setBorrowedBooks(member.getBorrowedBooks() + 1);

        Loan loan = loans.get(memberName);
        if (loan == null) {
            loans.put(memberName, new Loan(member, book));
        } else {
            for (int i = 0; i < loan.books.length; i++) {
                if (loan.books[i] == null) { loan.books[i] = book; break; }
            }
        }
        return "SUCCESS";
    }

    /**
     * GUI-friendly return.
     * @return "SUCCESS" or an error message
     */
    public static String returnGUI(String memberName, String bookTitle) {
        Member member = members.get(memberName);
        if (member == null) return "Member not found: " + memberName;//error handling if member not in system
        if (member.getBorrowedBooks() == 0) return memberName + " has not borrowed any books";//error handling if memeber not borrowed any book

        Loan loan = loans.get(memberName);
        if (loan == null) return "No loan record found for " + memberName;//error handling

        Book book = Book.GetAllBooks().get(bookTitle);
        if (book == null) return "Book not found: " + bookTitle;
        if (book.isAvailable()) return "\"" + bookTitle + "\" is not currently borrowed";//outputs if book is available

        book.setAvailable(true);
        for (int i = 0; i < loan.books.length; i++) {
            if (loan.books[i] == book) { loan.books[i] = null; break; }
        }
        member.setBorrowedBooks(member.getBorrowedBooks() - 1);
        return "SUCCESS";
    }

    /**
     * Returns a formatted string listing all books currently on loan to a member.
     */
    public static String getMemberLoansGUI(String memberName) {
        Member member = members.get(memberName);
        if (member == null) return "Member not found: " + memberName;
        if (member.getBorrowedBooks() == 0) return memberName + " has no borrowed books.";

        Loan loan = loans.get(memberName);
        if (loan == null) return memberName + " has no loan records.";//error handling

        StringBuilder sb = new StringBuilder(memberName + " has borrowed:\n");//output of how many books a member has borrowed
        for (Book b : loan.books) {
            if (b != null) sb.append("  \u2022 ").append(b.getTitle()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getLoanDetails() { return Arrays.toString(this.books); }

    @Override
    public String toString() {
        return "Member: " + member.getName() + "\nBooks: " + Arrays.toString(this.books);
    }
}
