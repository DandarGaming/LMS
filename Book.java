import java.util.HashMap;

public class Book extends LibraryItem {

    private String author;
    private String isbn;

    private static HashMap<String, Book> books = new HashMap<>();

    public Book(int itemId, String title, boolean available, String author, String isbn) {
        super(itemId, title, available);
        this.author = author;
        this.isbn = isbn;
        System.out.println("Book title: " + title + " Author: " + author + " Availability: " + available + " Isbn: " + isbn + " Item ID: " + itemId);
    }

    public String getAuthor(String title) {
        Book book = books.get(title);
        return book.author;
    }

    public void setAuthor(String author, String title) {
        Book book = books.get(title);
        book.author = author;
    }

    public String getIsbn(String title) {
        Book book = books.get(title);
        return book.isbn;
    }

    public void setIsbn(String isbn, String title) {
        Book book = books.get(title);
        book.isbn = isbn;
    }

    
    @Override
    public String toString() {
        return  " [ Title = " + getTitle() + "\n" +
                "Author = " + author + "\n" +
                "Isbn = " + isbn + "\n" +
                "Available = " + isAvailable() + " ]\n";
    }

    public static void AddBook(int itemId, String title, boolean available, String author, String isbn) {
        Book newbook = new Book(itemId, title, available, author, isbn);
        books.put(title, newbook);
        System.out.println("Your item has been successfully added: " + books.get(title));
    }

    public static HashMap<String, Book> GetBooks() {
        return books;
    }
}
