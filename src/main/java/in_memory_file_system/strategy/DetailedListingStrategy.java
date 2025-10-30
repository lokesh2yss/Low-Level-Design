package in_memory_file_system.strategy;

import in_memory_file_system.composite.Directory;
import in_memory_file_system.composite.FileSystemNode;

public class DetailedListingStrategy implements ListingStrategy{
    @Override
    public void list(Directory directory) {
        for(FileSystemNode node: directory.getChildren().values()) {
            char type = node instanceof Directory? 'd':'f';
            System.out.println(type + "\t" + node.getName() + "\t" + node.getCreatedTime());
        }
    }
}
