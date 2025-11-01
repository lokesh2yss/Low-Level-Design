package version_control_system.composite;

import java.util.HashMap;
import java.util.Map;

public class Directory extends FileSystemNode{
    private Map<String, FileSystemNode> children = new HashMap<>();
    public Directory(String name) {
        super(name);
    }

    public void addChild(FileSystemNode node) {
        this.children.put(node.getName(), node);
    }
    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public Map<String, FileSystemNode> getChildren() {
        return children;
    }

    @Override
    public FileSystemNode clone() {
        Directory newDir = new Directory(this.name);
        for(FileSystemNode child: children.values()) {
            newDir.addChild(child.clone());
        }
        return newDir;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "+ " + name + " (Directory)");
        for (FileSystemNode child : children.values()) {
            child.print(indent + "  ");
        }
    }
}
