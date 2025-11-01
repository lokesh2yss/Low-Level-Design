package version_control_system.composite;

public class File extends FileSystemNode{
    private String content;

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public FileSystemNode clone() {
        return new File(this.name, this.content);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "- " + name + " (File)");
    }
}
