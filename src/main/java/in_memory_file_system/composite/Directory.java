package in_memory_file_system.composite;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Directory extends FileSystemNode{
    private final Map<String, FileSystemNode> children = new ConcurrentHashMap<>();
    public Directory(String name, Directory parent) {
        super(name, parent);
    }
    public void addChild(FileSystemNode node) {
        children.put(node.getName(), node);
    }
    public FileSystemNode getChild(String name) {
        return children.get(name);
    }
    public Map<String, FileSystemNode> getChildren() {
        return Collections.unmodifiableMap(children);
    }
}
