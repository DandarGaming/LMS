import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryItem {
    
    private int itemId;
    private String title;
    private boolean available;

    Scanner scanner = new Scanner(System.in);

    public LibraryItem(int itemId, String title, boolean available) {
        // sout inputted id, inputted string, inputed availability
        this.itemId = itemId;
        this.title = title;
        this.available = available;
    }

    public int getItemId() {
        return itemId;
        }
    public void setItemId(int itemId) {
        // set itemId to the user input
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        // set title to user input
        this.title = title;
    }
        // mi tink me gwaan tek mi own life

    
    public boolean isAvailable() {
       return available;
        }

        // else
        //return ("Please select Y or N")
        else {
            System.out.println("Please select Y or N");
        }

        // restart loop until either Y or N selected
    } while (true);
}

    public void setAvailable(boolean available) {
        // if input = Y set book to available
        // if input = N set book to unavailable
        this.available = available;
    }

    @Override
    public String toString() {
        return """
               Book Info:
               Book ID Number: """ + itemId + "\n" +
               "Title: " + title + "\n" +
               "Availability: " + available;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<LibraryItem> books = new ArrayList<>();
        boolean running = true;

        while (running) {
            // shows user a menu to select the option they wanna do
            System.out.println("Choose an option:\n1) Add a book\n2) View added books\n3) Exit system");
            String choice = scanner.nextLine(); // reads your choice

            switch (choice) {
                case "1":
                    // create new item and add to list
                    LibraryItem item = new LibraryItem(0, "", false);
                    item.setItemId(item.getItemId());
                    item.setTitle(item.getTitle());
                    item.setAvailable(item.isAvailable());
                    books.add(item);
                    System.out.println("Book added.\n");
                    break;
                case "2":
                    // option 2: list all books that have been added so far
                    if (books.isEmpty()) {
                        System.out.println("No books added yet.\n");
                    } else {
                        System.out.println("--- Added Books ---");
                        for (LibraryItem b : books) {
                            System.out.println(b + "\n");
                        }
                    }
                    break;
                case "3":
                    // option 3: stops it from running by closing the loop
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    // if you put in an invalid option it asks you to retry
                    System.out.println("Invalid selection, please choose 1, 2, or 3.\n");
            }
        }
        scanner.close(); // close scanner before exiting
    }
}


// bug check and report any issues once all code is complete to make 
// sure its cross compatible and works from java main class
