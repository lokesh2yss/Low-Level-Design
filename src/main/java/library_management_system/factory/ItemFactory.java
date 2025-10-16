package library_management_system.factory;

import library_management_system.entities.Book;
import library_management_system.entities.Magazine;
import library_management_system.enums.ItemType;
import library_management_system.observer.LibraryItem;

public class ItemFactory {
    public static LibraryItem createItem(ItemType type, String id, String title, String author) {
        return switch (type) {
            case BOOK -> new Book(id, title, author);
            case MAGAZINE -> new Magazine(id, title, author); //author is publisher here
            default -> throw new IllegalArgumentException("Item type invalid");
        };
    }
}
