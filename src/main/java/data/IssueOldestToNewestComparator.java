package data;

import java.util.Comparator;

public class IssueOldestToNewestComparator implements Comparator<Issue> {
    public int compare (Issue issue1, Issue issue2) {
        return (int) (issue1.getCreateTime() - issue2.getCreateTime());
    }
}
