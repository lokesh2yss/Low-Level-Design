package library_management_system.strategy;

import library_management_system.observer.LibraryItem;

import java.util.List;

public interface SearchStrategy {
    List<LibraryItem> search(String query, List<LibraryItem> items);
}
