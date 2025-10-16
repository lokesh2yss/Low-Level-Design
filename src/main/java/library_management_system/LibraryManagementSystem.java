package library_management_system;

import library_management_system.entities.BookCopy;
import library_management_system.enums.ItemType;
import library_management_system.factory.ItemFactory;
import library_management_system.observer.LibraryItem;
import library_management_system.observer.Member;
import library_management_system.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagementSystem {
    private static final LibraryManagementSystem instance = new LibraryManagementSystem();
    private final Map<String, LibraryItem> catalog = new HashMap<>();
    private final Map<String, BookCopy> copies = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private SearchStrategy searchStrategy;
    private LibraryManagementSystem() {

    }
    public static LibraryManagementSystem getInstance() {
        return instance;
    }
    // --- Item Management ---
    public List<BookCopy> addItem(ItemType type, String id, String title, String author, int numCopies) {
        List<BookCopy> bookCopies = new ArrayList<>();
        LibraryItem item = ItemFactory.createItem(type, id, title, author);
        catalog.put(id, item);
        for(int i =0; i<numCopies; i++) {
            String copyId = id+"-c"+i+1;
            BookCopy copy = new BookCopy(copyId, item);
            //copies.put(copyId, new BookCopy(copyId, item));
            copies.put(copyId, copy);
            bookCopies.add(copy);
        }
        System.out.println("Added " + numCopies + " copies of '" + title + "'");
        return bookCopies;
    }

    // --- User Management ---
    public Member addMember(String id, String name) {
        Member member = new Member(id, name);
        members.put(id, member);
        return member;
    }

    // --- Core Actions ---
    public void checkout(String memberId, String copyId) {
        Member member = members.get(memberId);
        BookCopy copy = copies.get(copyId);
        if(member != null && copy != null) {
            copy.checkout(member);
        }
        else {
            System.out.println("Error: Invalid member or copy ID.");
        }
    }
    public void returnItem(String copyId) {
        BookCopy copy = copies.get(copyId);
        if(copy != null) {
            copy.returnItem();
        }
        else {
            System.out.println("Error: Invalid copy ID.");
        }
    }
    public void placeHold(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);
        if(member != null && item != null) {
            item.getCopies()
                    .stream()
                    .filter(copy -> !copy.isAvailable())
                    .findFirst()
                    .ifPresent(copy -> copy.placeHold(member));
        }
    }
    public List<LibraryItem> search(String query, SearchStrategy strategy) {
        return strategy.search(query, new ArrayList<>(catalog.values()));
    }
    public void printCatalog() {
        System.out.println("\n--- Library Catalog ---");
        catalog.values().forEach(item -> System.out.printf("ID: %s, Title: %s, Author/Publisher: %s, Available: %d\n",
                item.getId(), item.getTitle(), item.getAuthorOrPublisher(), item.getAvailableCopyCount()));
        System.out.println("-----------------------\n");
    }
}
