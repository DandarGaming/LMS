import java.util.Scanner;

public class Loan {

    private Member member;
    private Book book;
    
    static Scanner scanner = new Scanner(System.in);
    static int userIntInput;
    static boolean exitProgram = false;
    static int totalBooks = 0;
    static String bookTitle;
    static String bookAuthor;
    static String bookIsbn;
    static String memberName;
    static int memberId;
    static String memberEmail;


    public Loan(Member member, Book book) {
    this.member = member;
    this.book = book;
    }

    // public Member getMember() {

    // }

    public void setMember() {
        System.out.println("Enter the name of the new member: ");
        memberName = scanner.nextLine();
        System.out.println("Enter the ID for this new member: ");
        memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new members email: ");
        memberEmail = scanner.nextLine();
        Member newmember = new Member(memberName, memberId, memberEmail, 0); 
    }

    public Book getBook() {
        String title = scanner.nextLine();
        book = Book.GetBook(title);
        return book;
    }

    public void setBook() {
        System.out.println("Enter the title of the book: ");
        bookTitle = scanner.nextLine();
        System.out.println("Enter the name of the author: ");
        bookAuthor = scanner.nextLine();
        System.out.println("Enter the books Isbn: ");
        bookIsbn = scanner.nextLine();
        Book.AddBook(totalBooks + 1, bookTitle, true, bookAuthor, bookIsbn);
    }

    public String getLoanDetails() {
        System.out.println("Enter the name of the person who you wish to check the loan details of");
        memberName = scanner.nextLine();
        return member.getBorrowedBooks();
    }

    // @Override
    // public String toString() {
    // Does this file need a toString??? It stores no constant information on anything
    // }
}
