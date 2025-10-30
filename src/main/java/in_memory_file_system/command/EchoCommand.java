package in_memory_file_system.command;

import in_memory_file_system.entities.FileSystem;

public class EchoCommand implements Command{
    private final FileSystem fs;
    private final String content;
    private final String filePath;

    public EchoCommand(FileSystem fs, String content, String filePath) {
        this.fs = fs;
        this.content = content;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        // The '>' redirection character is handled implicitly by the command's nature.
        // In a more complex shell, this would be more sophisticated.
        fs.writeToFile(filePath, content);

    }
}
