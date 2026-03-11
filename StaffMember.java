public class StaffMember extends Member {

    private final int maxBooks = 10;

    public StaffMember(String name, int memberId, String email, int borrowedBooks) {
        super(name, memberId, email, borrowedBooks);
    }

    public int getMaxBooks() {

    }

    @Override
    public String toString() {

    }
}
