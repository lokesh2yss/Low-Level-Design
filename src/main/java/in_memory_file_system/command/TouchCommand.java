package in_memory_file_system.command;
import in_memory_file_system.entities.FileSystem;

public class TouchCommand implements Command{
    private final FileSystem fs;
    private final String path;

    public TouchCommand(FileSystem fs, String path) {
        this.fs = fs;
        this.path = path;
    }

    @Override
    public void execute() {
        fs.createFile(path);
    }
}
