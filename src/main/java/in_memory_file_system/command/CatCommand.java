package in_memory_file_system.command;

import in_memory_file_system.entities.FileSystem;

public class CatCommand implements Command{
    private final FileSystem fs;
    private final String path;

    public CatCommand(FileSystem fs, String path) {
        this.fs = fs;
        this.path = path;
    }


    @Override
    public void execute() {
        String content = fs.readFile(path);
        if(content != null && !content.isEmpty()) {
            System.out.println(content);
        }
    }
}
