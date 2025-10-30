package in_memory_file_system.strategy;

import in_memory_file_system.composite.Directory;

public interface ListingStrategy {
    void list(Directory directory);
}
