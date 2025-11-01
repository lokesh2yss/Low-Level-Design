package version_control_system.entities;

import java.util.HashMap;
import java.util.Map;

public class BranchManager {
    private final Map<String, Branch> branchs = new HashMap<>();
    private Branch currentBranch;
    public BranchManager(Commit initialCommit) {
        // Create the main branch pointing to the initial commit
        Branch mainBranch = new Branch("main", initialCommit);
        branchs.put("main", mainBranch);
        this.currentBranch = mainBranch;
    }
    public void createBranch(String name, Commit head) {
        if(branchs.containsKey(name)) {
            System.out.println("Error: Branch " +name+ " already exists");
            return;
        }
        Branch newBranch = new Branch(name, head);
        branchs.put(name, newBranch);
        System.out.println("Created branch '" + name + "'.");
    }
    public boolean switchBranch(String name) {
        if(!branchs.containsKey(name)) {
            System.out.println("Error: Branch "+name+ " doesn't exit");
            return false;
        }
        this.currentBranch = branchs.get(name);
        System.out.println("Switched to branch '" + name + "'.");
        return true;
    }
    public void updateHead(Commit newHead) {
        this.currentBranch.setHead(newHead);
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }
}
