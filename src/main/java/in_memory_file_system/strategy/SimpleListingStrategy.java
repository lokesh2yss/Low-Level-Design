package in_memory_file_system.strategy;

import in_memory_file_system.composite.Directory;

public class SimpleListingStrategy implements ListingStrategy{
    @Override
    public void list(Directory directory) {
        directory.getChildren().keySet().forEach(name -> System.out.print(name + "  "));
        System.out.println();
    }
}
