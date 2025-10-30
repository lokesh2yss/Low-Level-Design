package in_memory_file_system.command;
import in_memory_file_system.entities.FileSystem;

public class PwdCommand implements Command{
    private final FileSystem fs;

    public PwdCommand(FileSystem fs) {
        this.fs = fs;
    }

    @Override
    public void execute() {
        System.out.println(fs.getWorkingDirectory());
    }
}
