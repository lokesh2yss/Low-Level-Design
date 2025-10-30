package in_memory_file_system.composite;

public class File extends FileSystemNode{
    private String content;
    public File(String name, Directory parent) {
        super(name, parent);
        this.content = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
