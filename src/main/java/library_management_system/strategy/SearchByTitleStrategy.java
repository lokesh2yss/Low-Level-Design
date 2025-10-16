package library_management_system.strategy;

import library_management_system.observer.LibraryItem;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByTitleStrategy implements SearchStrategy{
    @Override
    public List<LibraryItem> search(String query, List<LibraryItem> items) {
        return items.stream()
                .filter(libraryItem -> libraryItem.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
