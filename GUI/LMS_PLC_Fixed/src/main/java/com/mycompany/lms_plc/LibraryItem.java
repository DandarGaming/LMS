package com.mycompany.lms_plc;

public class LibraryItem {

    private int itemId;
    private String title;
    private boolean available;

    public LibraryItem(int itemId, String title, boolean available) {
        this.itemId = itemId;
        this.title = title;
        this.available = available;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Book ID: " + itemId + "\nTitle: " + title + "\nAvailability: " + available;
    }
}
