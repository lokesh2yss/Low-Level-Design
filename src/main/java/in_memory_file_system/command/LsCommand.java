package in_memory_file_system.command;


import in_memory_file_system.entities.FileSystem;
import in_memory_file_system.strategy.ListingStrategy;

public class LsCommand implements Command{
    private final FileSystem fs;
    private final String path; // Path can be null, meaning "current directory"
    private final ListingStrategy listingStrategy;

    public LsCommand(FileSystem fs, String path, ListingStrategy listingStrategy) {
        this.fs = fs;
        this.path = path;
        this.listingStrategy = listingStrategy;
    }

    @Override
    public void execute() {
        if(path == null) {
            fs.listContent(listingStrategy);
        }
        else {
            fs.listContent(path, listingStrategy);
        }
    }
}
