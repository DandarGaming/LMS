package com.mycompany.lms_plc;

import java.util.Arrays;
import java.util.HashMap;

public class Loan {

    private Member member;
    static Member staticMember;
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

    public static Member getMember(String memberName) { 
        staticMember = members.get(memberName);
        return staticMember;
    }

    /**
     * GUI-friendly member addition.
     * @param type "Student" or "Staff"
     * @return "SUCCESS" or an error message
     */
    public static String addMemberGUI(String type, String name, int memberId, String email) {
        if (name == null || name.isBlank()) return "Name cannot be empty";
        if (members.containsKey(name)) return "A member with that name already exists";
        switch (type) {
            case "Student" -> members.put(name, new StudentMember(name, memberId, email, 0));
            case "Staff"   -> members.put(name, new StaffMember(name, memberId, email, 0));
            default        -> { return "Invalid member type: " + type; }
        }
        return "SUCCESS";
    }

    /**
     * GUI-friendly member removal.
     * @return "SUCCESS" or an error message
     */
    public static String removeMemberGUI(String name) {
        if (!members.containsKey(name)) return "Member not found: " + name;
        members.remove(name);
        loans.remove(name);
        return "SUCCESS";
    }

    /**
     * GUI-friendly member info retrieval.
     */
    public static String getMemberInfoGUI(String name) {
        Member m = members.get(name);
        return m == null ? "Member not found: " + name : m.toString();
    }

    // ── Loan management ───────────────────────────────────────────────────────

    /**
     * GUI-friendly borrow.
     * @return "SUCCESS" or an error message
     */
    public static String borrowGUI(String memberName, String bookTitle) {
        Member member = members.get(memberName);
        if (member == null) return "Member not found: " + memberName;
        if (member.getBorrowedBooks() >= member.getMaxBooks())
            return memberName + " has reached the borrow limit (" + member.getMaxBooks() + " books)";

        Book book = Book.GetAllBooks().get(bookTitle);
        if (book == null) return "Book not found: " + bookTitle;
        if (!book.isAvailable()) return "\"" + bookTitle + "\" is not currently available";

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
        if (member == null) return "Member not found: " + memberName;
        if (member.getBorrowedBooks() == 0) return memberName + " has not borrowed any books";

        Loan loan = loans.get(memberName);
        if (loan == null) return "No loan record found for " + memberName;

        Book book = Book.GetAllBooks().get(bookTitle);
        if (book == null) return "Book not found: " + bookTitle;
        if (book.isAvailable()) return "\"" + bookTitle + "\" is not currently borrowed";

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
        if (loan == null) return memberName + " has no loan records.";

        StringBuilder sb = new StringBuilder(memberName + " has borrowed:\n");
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
