import java.util.Scanner;

public class LibraryItem {
    
    private int itemId;
    private String title;
    private boolean available;

    Scanner scanner = new Scanner(System.in);

    public LibraryItem(int itemId, String title, boolean available) {
        // sout inputted id, inputted string, inputed availability
    }

    public int getItemId() {
        // prompt user to input the Item ID (integer)
        System.out.prntln("Enter Item ID: ")
        itemId = scanner.nextInt();
        return itemId;
        System.out.println("You have entered: " + itemId);
    }

    public void setItemId(int itemId) {
        // set itemId to the user input
        this.itemId = itemId;
    }

    public String getTitle() {
        System.out.println("Enter The Title: ")
        // prompt user to input the title (String)
        title = scanner.nextLine();
        return title;
        System.out.println("The title is: " + title);
    }

    public void setTitle(String title) {
        // set title to user input
        this.title = title;
    }

    public boolean isAvailable() {
        System.out.println("Is the book currently available? (Y/N): ")
        // ask user to type Y or N to know if the book is currently available
    }

    public void setAvailable(boolean available) {
        // if input = Y set book to available
        // if input = N set book to unavailable
    }

    @Override
    public String toString() {

    }
}
