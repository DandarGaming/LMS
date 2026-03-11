public class LibraryItem {

import java.util.Scanner;
    
    private int itemId;
    private String title;
    private boolean available;

    public LibraryItem(int itemId, String title, boolean available) {
        // sout inputted id, inputted string, inputed availability
    }

    public int getItemId() {
        System.out.prntln("Enter Item ID: ")
        // prompt user to input the Item ID (integer)
    }

    public void setItemId(int itemId) {
        // set itemId to the user input
    }

    public String getTitle() {
        System.out.println("Enter The Title: ")
        // prompt user to input the title (String)
    }

    public void setTitle(String title) {
        // set title to user input
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
