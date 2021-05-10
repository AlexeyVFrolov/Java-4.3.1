package data;

import java.util.Comparator;

public class IssueNewestToOldestComparator implements Comparator<Issue> {
    public int compare (Issue issue1, Issue issue2) {
        return (int) (issue2.getCreateTime() - issue1.getCreateTime());
    }
}
