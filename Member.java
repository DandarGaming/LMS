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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int calculateBorrowLimit() {
        return 0; // default output, will be overrided by StaffMember and StudentMember classes
    }

    public int getMaxBooks() {
        return 0; // default output, will be overrided by StaffMember and StudentMember classes
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nMember ID: " + this.memberId +
                "\nEmail: " + this.email +
                "\nBorrowed Books: " + this.borrowedBooks;
    }
}
