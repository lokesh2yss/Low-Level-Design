package version_control_system.entities;

public class Branch {
    private String name;
    private Commit head;
    public Branch(String name, Commit head) {
        this.name = name;
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public Commit getHead() {
        return head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHead(Commit head) {
        this.head = head;
    }
}
