public class StudentMember extends Member {

    private final int maxBooks = 5;

    public StudentMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);

    }
    
    public int getMaxBooks() {
    return maxBooks;
    }

    @Override
    public String toString() {
    return super.toString() + "Max Books: " + maxBooks;
    }
}
