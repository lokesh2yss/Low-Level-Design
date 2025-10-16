package library_management_system.entities;

import library_management_system.observer.LibraryItem;

public class Magazine extends LibraryItem {
    private final String publisher;
    public Magazine(String id, String title, String publisher) {
        super(id, title);
        this.publisher = publisher;
    }
    @Override
    public String getAuthorOrPublisher() {
        return publisher;
    }
}
