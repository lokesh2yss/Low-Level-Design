package in_memory_file_system.composite;

import java.time.Instant;

public abstract class FileSystemNode {
    protected String name;
    protected Directory parent;
    protected Instant createdTime;
    public FileSystemNode(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.createdTime = Instant.now();
    }

    public String getPath() {
        if(parent == null) {
            return name;
        }
        if(parent.getParent() == null) {
            return parent.getPath()+name;
        }
        return parent.getPath()+"/"+name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }
}
