package version_control_system;

import version_control_system.composite.Directory;
import version_control_system.entities.BranchManager;
import version_control_system.entities.Commit;
import version_control_system.entities.CommitManager;

public class VersionControlSystem {
    private static VersionControlSystem instance;
    private CommitManager commitManager;
    private BranchManager branchManager;
    private Directory workingDirectory;
    private VersionControlSystem() {
        this.commitManager = new CommitManager();
        // Initialize with a root directory
        this.workingDirectory = new Directory("root");
        // Create the first commit (initial state)
        Commit initialCommit = commitManager.createCommit( "system" , "Initial commit",null, (Directory) workingDirectory.clone());
        // Initialize branch manager with the first commit
        this.branchManager = new BranchManager(initialCommit);
    }
    public synchronized static VersionControlSystem getInstance() {
        if(instance == null) {
            instance = new VersionControlSystem();
        }
        return instance;
    }

    public Directory getWorkingDirectory() {
        return workingDirectory;
    }
    public String commit(String author, String message) {
        Commit parentCommit = branchManager.getCurrentBranch().getHead();
        Directory snapshot = (Directory) workingDirectory.clone();
        Commit newCommit = commitManager.createCommit(author, message, parentCommit, snapshot);
        branchManager.updateHead(newCommit);

        System.out.println("Committed " + newCommit.getId() + " to branch " + branchManager.getCurrentBranch().getName());
        return newCommit.getId();
    }
    public void createBranch(String name) {
        Commit head = branchManager.getCurrentBranch().getHead();
        branchManager.createBranch(name, head);
    }
    public void checkoutBranch(String name) {
        boolean success = branchManager.switchBranch(name);
        if(success) {
            // On successful switch, revert working directory to the new branch's head
            Commit newHead = branchManager.getCurrentBranch().getHead();
            this.workingDirectory = (Directory) newHead.getRootSnapshot().clone();
        }
    }
    public void revert(String commitId) {
        Commit targetCommit = commitManager.getCommit(commitId);
        if (targetCommit == null) {
            System.out.println("Error: Commit '" + commitId + "' not found.");
            return;
        }
        // Note: This is a deep clone to prevent the working dir from modifying a historical snapshot
        this.workingDirectory = (Directory) targetCommit.getRootSnapshot().clone();
        branchManager.updateHead(targetCommit);
        System.out.println("Repository state reverted to commit " + commitId);
    }
    public void log() {
        System.out.println("\n--- Commit History for branch '" + branchManager.getCurrentBranch().getName() + "' ---");
        Commit headCommit = branchManager.getCurrentBranch().getHead();
        commitManager.printHistory(headCommit);
    }

    public void printCurrentState() {
        System.out.println("\n--- Current Working Directory State ---");
        workingDirectory.print("");
    }
}
