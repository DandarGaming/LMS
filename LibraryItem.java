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
        System.out.println("You have entered: " + itemId);
        return itemId;
    }

    public void setItemId(int itemId) {
        // set itemId to the user input
        this.itemId = itemId;
    }

    public String getTitle() {
        System.out.println("Enter The Title: ")
        // prompt user to input the title (String)
        title = scanner.nextLine();
        System.out.println("The title is: " + title);
        return title;
    }

    public void setTitle(String title) {
        // set title to user input
        this.title = title;
    }

    public boolean isAvailable() {
        System.out.println("Is the book currently available? (Y/N): ")
        // mi tink me gwaan tek mi own life
        // ask user to type Y or N to know if the book is currently available
        // pseudo code of what the code should do:
    
        //if input = Y (either case)
        //return ("Book is available")

        //if input = N (either case)
        //return ("Book is unavailable")

        //else
        //return ("Please select Y or N")
        //restart loop until either y or N selected
    }

    public void setAvailable(boolean available) {
        // if input = Y set book to available
        // if input = N set book to unavailable
    }

    @Override
    public String toString() {

    }
}

what is this diddy blud doing 
