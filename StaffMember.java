public class StaffMember extends Member {

    private final int maxBooks = 10;

    public StaffMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    public int getMaxBooks() {
    return maxBooks; // returns 10 as its strored in line 3 
    }

    @Override //overloads 
    public String toString() {
    return super.toString() + "Max Books: " + maxBooks;

    }
}
