package in_memory_file_system.command;
import in_memory_file_system.entities.FileSystem;

public class CdCommand implements Command{
    private final FileSystem fs;
    private final String path;

    public CdCommand(FileSystem fs, String path) {
        this.fs = fs;
        this.path = path;
    }

    @Override
    public void execute() {
        fs.changeDirectory(path);
    }
}
