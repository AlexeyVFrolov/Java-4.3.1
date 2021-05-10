package data;

import lombok.Data;

@Data
public class Issue {
    private int id;
    private String author;
    private String label;
    private String assignee;
    private boolean status; // открыт - true
    private final long createTime;

    public Issue (int id, String author, String label, String assignee) {
        this.id = id;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
        this.status = true;
        this.createTime = System.currentTimeMillis();
    }
}
