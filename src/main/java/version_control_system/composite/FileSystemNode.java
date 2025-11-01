package version_control_system.composite;

public abstract class FileSystemNode {
    protected String name;
    public FileSystemNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract FileSystemNode clone();
    public abstract void print(String indent);
}
