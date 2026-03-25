public class StaffMember extends Member {

    private final int maxBooks = 10;// maxBooks set as 10

    public StaffMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    @Override
    public int getMaxBooks() {
    return maxBooks; // returns maxBooks for StaffMember Class(10)
    }

    @Override
    public int calculateBorrowLimit() {
        return maxBooks; // Overrides the default output with maxBooks of StaffMember
    }
    
    @Override //overloads 
    public String toString() {
    return super.toString() + "Max Books: " + maxBooks;

    }
}
