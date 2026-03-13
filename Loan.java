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

    }

    public Member getMember() {
        
    }

    public void setMember() {
        System.out.println("Enter the name of the new member");
        memberName = scanner.nextLine();
        System.out.println("");

    }

    public static String getBook() {
        System.out.println("Enter the title of the book you wish to view: ");
        String title = scanner.nextLine();
        String bookInfo = Book.GetBook(title);
        return bookInfo;
        
    }

    static public void setBook() {
        System.out.println("Enter the title of the book: ");
        bookTitle = scanner.nextLine();
        System.out.println("Enter the name of the author: ");
        bookAuthor = scanner.nextLine();
        System.out.println("Enter the books Isbn: ");
        bookIsbn = scanner.nextLine();
        Book.AddBook(totalBooks + 1, bookTitle, true, bookAuthor, bookIsbn);
    }

    public String getLoanDetails() {
        return Member.getBorrowedBooks();
    }

    // @Override
    // public String toString() {
    // Does this file need a toString??? It stores no constant information on anything
    // }
}
