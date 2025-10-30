package in_memory_file_system.command;
import in_memory_file_system.entities.FileSystem;

public class MkdirCommand implements Command{
    private final FileSystem fs;
    private final String path;

    public MkdirCommand(FileSystem fs, String path) {
        this.fs = fs;
        this.path = path;
    }

    @Override
    public void execute() {
        fs.createDirectory(path);
    }
}
