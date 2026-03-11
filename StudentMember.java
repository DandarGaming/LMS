public class StudentMember extends Member {

    private final int maxBooks = 5;

    public StudentMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    public int getMaxBooks() {

    }

    @Override
    public String toString() {

    }
}
