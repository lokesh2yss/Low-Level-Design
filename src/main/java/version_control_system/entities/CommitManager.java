package version_control_system.entities;

import version_control_system.composite.Directory;

import java.util.HashMap;
import java.util.Map;

public class CommitManager {
    private final Map<String, Commit> commits = new HashMap<>();
    public Commit createCommit(String message, String author, Commit parent, Directory rootSnapshot) {
        Commit commit = new Commit(message, author, parent, rootSnapshot);
        commits.put(commit.getId(), commit);
        return commit;
    }
    public Commit getCommit(String commitId) {
        return commits.get(commitId);
    }
    public void printHistory(Commit headCommit) {
        if(headCommit == null) {
            System.out.println("No commits in history.");
            return;
        }
        Commit current = headCommit;
        while (current != null) {
            System.out.println("Commit: " + current.getId());
            System.out.println("Author: " + current.getAuthor());
            System.out.println("Date: " + current.getTimestamp());
            System.out.println("Message: " + current.getMessage());
            System.out.println("--------------------");
            current = current.getParent();
        }
    }
}
