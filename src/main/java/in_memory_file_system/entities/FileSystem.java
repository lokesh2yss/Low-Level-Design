package in_memory_file_system.entities;

import in_memory_file_system.composite.Directory;
import in_memory_file_system.composite.File;
import in_memory_file_system.composite.FileSystemNode;
import in_memory_file_system.strategy.ListingStrategy;

public class FileSystem {
    private static volatile FileSystem instance;
    private final Directory root;
    private Directory currentDirectory;
    private FileSystem() {
        this.root = new Directory("/", null);
        this.currentDirectory = root;
    }
    public static FileSystem getInstance() {
        if(instance == null) {
            synchronized (FileSystem.class) {
                if(instance == null) {
                    instance = new FileSystem();
                }
            }
        }
        return instance;
    }
    public void createDirectory(String path) {
        createNode(path, true);
    }
    public void createFile(String path) {
        createNode(path, false);
    }
    public void changeDirectory(String path) {
        FileSystemNode node = getNode(path);
        if(node instanceof Directory) {
            currentDirectory = (Directory) node;
        }
        else {
            System.out.println("Error: '" + path + "' is not a directory.");
        }
    }
    public void listContent(ListingStrategy strategy) {
        strategy.list(currentDirectory);
    }
    public void listContent(String path, ListingStrategy strategy) {
        FileSystemNode node = getNode(path);
        if (node == null) {
            System.err.println("ls: cannot access '" + path + "': No such file or directory");
            return;
        }

        if (node instanceof Directory) {
            strategy.list((Directory) node);
        } else {
            // Mimic Unix behavior: if ls is pointed at a file, it just prints the file name.
            System.out.println(node.getName());
        }
    }
    public String getWorkingDirectory() {
        return currentDirectory.getPath();
    }
    public void writeToFile(String path, String content) {
        FileSystemNode node = getNode(path);
        if(node instanceof File) {
            ((File) node).setContent(content);
        }
        else {
            System.out.println("Error: Cannot write to '" + path + "'. It is not a file or does not exist.");
        }
    }
    public String readFile(String path) {
        FileSystemNode node = getNode(path);
        if(node instanceof File) {
            return ((File) node).getContent();
        }
        System.out.println("Error: Cannot read from '" + path + "'. It is not a file or does not exist.");
        return "";
    }
    private void createNode(String path, boolean isDirectory) {
        String name;
        Directory parent;
        System.out.println(currentDirectory.getName());
        if(path.contains("/")) {
            int lastSlashIndex = path.lastIndexOf("/");
            name = path.substring(lastSlashIndex+1);
            String parentPath = path.substring(0, lastSlashIndex);

            //handle creating in root
            if(parentPath.isEmpty()) {
                parentPath = "/";
            }
            FileSystemNode parentNode = getNode(parentPath);
            if (!(parentNode instanceof Directory)) {
                System.out.println("Error: Invalid path. Parent '" + parentPath + "' is not a directory or does not exist.");
                return;
            }
            parent = (Directory) parentNode;
        }
        else {
            name = path;
            parent = currentDirectory;
        }
        if (name.isEmpty()) {
            System.err.println("Error: File or directory name cannot be empty.");
            return;
        }
        if (parent.getChild(name) != null) {
            System.out.println("Error: Node '" + name + "' already exists in '" + parent.getPath() + "'.");
            return;
        }
        FileSystemNode newNode = isDirectory ? new Directory(name, parent) : new File(name, parent);
        parent.addChild(newNode);
    }
    private FileSystemNode getNode(String path) {
        if(path.equals("/")) return root;
        Directory startDir = path.startsWith("/")? root: currentDirectory;

        // Use a non-empty string split to handle leading/trailing slashes gracefully
        String[] parts = path.split("/");

        FileSystemNode current = startDir;
        for(String part: parts) {
            if(part.isEmpty() || part.equals(".")) continue;
            if (!(current instanceof Directory)) {
                return null; // Part of the path is a file, so it's invalid
            }

            if (part.equals("..")) {
                current = current.getParent();
                if (current == null) current = root; // Can't go above root
            } else {
                current = ((Directory) current).getChild(part);
            }

            if (current == null) return null; // Path component does not exist

        }
        return current;
    }
}
