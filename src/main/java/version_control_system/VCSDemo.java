package version_control_system;

import version_control_system.composite.Directory;
import version_control_system.composite.File;

public class VCSDemo {
    public static void main(String[] args) {
        System.out.println("Initializing Version Control System...");
        VersionControlSystem vcs = VersionControlSystem.getInstance();

        // --- Initial State on 'main' branch ---
        vcs.printCurrentState();

        // --- First Commit ---
        System.out.println("\n1. Making initial changes and committing...");
        Directory root = vcs.getWorkingDirectory();
        root.addChild(new File("README.md", "This is a simple VCS."));
        Directory srcDir = new Directory("src");
        root.addChild(srcDir);
        srcDir.addChild(new File("Main.java", "public class Main {}"));
        String firstCommitId = vcs.commit("Alice", "Add README and initial source structure");
        vcs.printCurrentState();

        // --- Second Commit ---
        System.out.println("\n2. Modifying a file and committing again...");
        File readme = (File) root.getChild("README.md");
        readme.setContent("This is an in-memory version control system.");
        String secondCommitId = vcs.commit("Alice", "Update README documentation");
        vcs.printCurrentState();

        // --- View History ---
        vcs.log();

        // --- Branching ---
        System.out.println("\n3. Creating a new branch 'feature/add-tests'...");
        vcs.createBranch("feature/add-tests");
        vcs.checkoutBranch("feature/add-tests");
        root = vcs.getWorkingDirectory();
        System.out.println("\n4. Working on the new branch...");
        Directory testDir = new Directory("tests");
        root.addChild(testDir);
        testDir.addChild(new File("VCS_Test.java", "import org.junit.Test;"));
        String featureCommitId = vcs.commit("Bob", "Add test directory and initial test file");
        vcs.printCurrentState();

        // --- View history on feature branch ---
        vcs.log();

        // --- Switch back to main ---
        System.out.println("\n5. Switching back to 'main' branch...");
        vcs.checkoutBranch("main");
        // Notice the 'tests' directory is gone, as it only exists on the feature branch.
        vcs.printCurrentState();
        vcs.log(); // Log shows only main branch history

        // --- Reverting ---
        System.out.println("\n6. Reverting 'main' branch to the first commit...");
        vcs.revert(firstCommitId);
        vcs.printCurrentState(); // The README content is back to its original state.

        // --- View history after revert ---
        System.out.println("\nHistory of 'main' after reverting:");
        vcs.log(); // The head is now the first commit
    }
}
