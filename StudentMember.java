public class StudentMember extends Member {

    private final int maxBooks = 5; ///sets maxBooks as 5 within the StudentMember Class

    public StudentMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);

    }
    
    public int getMaxBooks() {
    return maxBooks; // returns student's maxBooks (5)
    }
    
    @Override
     public int calculateBorrowLimit() {
        return maxBooks; // Overrides the default output with maxBooks of StudentMember
    }
    
    @Override
    public String toString() {
    return super.toString() + " Max Books: " + maxBooks; 
    }
}
