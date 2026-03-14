import java.util.HashMap;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    private static final HashMap<String, Book> books = new HashMap<>();
    

    public Book(int itemId, String title, boolean available, String author, String isbn) {
        super(itemId, title, available);
        this.author = author;
        this.isbn = isbn;
        System.out.println("Book title: " + title + " Author: " + author + " Availability: " + available + " Isbn: " + isbn + " Item ID: " + itemId);
    }

    public String getAuthor() {
        if (books.isEmpty()) {
            return "Sorry but no books are in the system";
        }
        else {
            return this.author;
        }
    }

    public void setAuthor() {
        if (books.isEmpty()) {
            System.out.println("Sorry but no books are in the system");
        }
        else {
            System.out.println("Enter the name of the author for this book");
            String newAuthor = scanner.nextLine();
            this.author = newAuthor;
        }
    }

    public String getIsbn() {
        if (books.isEmpty()) {
            return "Sorry but no books are in the system";
        }
        else {
            return this.isbn;
        }
    }

    public void setIsbn() {
        if (books.isEmpty()) {
            System.out.println("Sorry but no books are in the system");
        }
        else {
            System.out.println("Enter the Isbn for this book");
            String newIsbn = scanner.nextLine();
            this.isbn = newIsbn;
        }
       
    }

    
    @Override
    public String toString() {
        return  " [ Title = " + getTitle() + "\n" +
                "Author = " + this.author + "\n" +
                "Isbn = " + this.isbn + "\n" +
                "Available = " + isAvailable() + " ]\n";
    }

    public static void AddBook(int itemId, String title, boolean available, String author, String isbn) {
        Book newbook = new Book(itemId, title, available, author, isbn);
        books.put(title, newbook);
        System.out.println("Your item has been successfully added: " + books.get(title));
    }

    public static HashMap<String, Book> GetAllBooks() {
        if (books == null) {
            System.out.println("Sorry but no books are in the system");
            return books;
        }
        else {
            return books;
        }
        
    }

    public static String GetBookString(String title) {
        Book book = books.get(title);
        return book.toString();
    }

    public static Book GetBook(String title) {
        Book book = books.get(title);
        if (book == null) {
            System.out.println("We dont have that book in out system");
        }
        return book;
    }
}
