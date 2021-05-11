package manager;

import data.Issue;
import data.IssueNewestToOldestComparator;
import data.IssueOldestToNewestComparator;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    IssueRepository issueRepository = new IssueRepository();

    public void addIssue(Issue issue) {

        issueRepository.add(issue);
    }

    public List<Issue> getAll() {

        return issueRepository.getAll();
    }

    private List<Issue> filterBy(Predicate<Issue> issuePredicate) {
        List<Issue> filteredIssues = new ArrayList<>();

        for (Issue issue : issueRepository.getAll()) {
            if (issuePredicate.test(issue)) {
                filteredIssues.add(issue);
            }
        }

        return filteredIssues;
    }

    public List<Issue>  getOpenedIssueList() {
        Predicate<Issue> isOpened = Issue::isStatus;

        List<Issue> openedIssueList = new ArrayList<>(this.filterBy(isOpened));
        return openedIssueList;
    }

    public List<Issue> getClosedIssueList() {
        Predicate<Issue> isOpened = issue -> !issue.isStatus();

        List<Issue> closedIssueList = new ArrayList<>(this.filterBy(isOpened));
        return closedIssueList;
    }

    public List<Issue> filterByAuthor(String author) {
        Predicate<Issue> p = issue -> issue.getAuthor().equalsIgnoreCase(author);
        return this.filterBy(p);
    }

    public List<Issue> filterByLabel(String label) {
        Predicate<Issue> p = issue -> issue.getLabel().contains(label);
        return this.filterBy(p);
    }

    public List<Issue> filterByAssignee(String assignee) {
        Predicate<Issue> p = issue -> issue.getAssignee().equalsIgnoreCase(assignee);
        return this.filterBy(p);
    }

    public List<Issue> sortIssues(boolean newestToOldestOrder) {
        List<Issue> sortedIssues = new ArrayList<>(issueRepository.getAll());

        if (newestToOldestOrder) {
            IssueNewestToOldestComparator c = new IssueNewestToOldestComparator();
            sortedIssues.sort(c);
        } else {
            IssueOldestToNewestComparator c = new IssueOldestToNewestComparator();
            sortedIssues.sort(c);
        }
        return sortedIssues;
    }

    public void setStatus(int issueId, boolean openIssue) {

        issueRepository.getById(issueId).setStatus(openIssue);
    }
}
