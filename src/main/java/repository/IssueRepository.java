package repository;

import data.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> getAll() {
        return issues;
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public boolean add(Issue issue) {

        return issues.add(issue);
    }

    public boolean remove(Issue issue) {

        return issues.remove(issue);
    }

    public boolean addAll(Collection<? extends Issue> issue) {
        return this.issues.addAll(issue);
    }

    public boolean removeAll(Collection<? extends Issue> issue) {
        return this.issues.removeAll(issue);
    }
}
