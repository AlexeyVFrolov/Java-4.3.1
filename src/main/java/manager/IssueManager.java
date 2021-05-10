package manager;

import data.Issue;
import data.IssueNewestToOldestComparator;
import data.IssueOldestToNewestComparator;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    IssueRepository issueRepository = new IssueRepository();

    public void addIssue(Issue issue) {

        issueRepository.add(issue);
    }

    public List<Issue> getAll() {

        return issueRepository.getAll();
    }

    public List<Issue>  getOpenedIssueList() {
        Predicate<Issue> isOpened = Issue::isStatus;
        List<Issue> openedIssueList = new ArrayList<>();

        for (Issue issue : this.filterBy(isOpened)) {
            openedIssueList.add(issue);
        }
        return openedIssueList;
    }

    public List<Issue> getClosedIssueList() {
        Predicate<Issue> isOpened = issue -> !issue.isStatus();
        List<Issue> openedIssueList = new ArrayList<>();

        for (Issue issue : this.filterBy(isOpened)) {
            openedIssueList.add(issue);
        }
        return openedIssueList;
    }

    public List<Issue> filterBy(Predicate<Issue> issuePredicate) {
        List<Issue> filteredIssues = new ArrayList<>();

        for (Issue issue : issueRepository.getAll()) {
            if (issuePredicate.test(issue)) {
                filteredIssues.add(issue);
            }
        }

        return filteredIssues;
    }

    public void sortIssues(boolean newestToOldestOrder) {

        if (newestToOldestOrder) {
            IssueNewestToOldestComparator c = new IssueNewestToOldestComparator();
            issueRepository.getAll().sort(c);
        } else {
            IssueOldestToNewestComparator c = new IssueOldestToNewestComparator();
            issueRepository.getAll().sort(c);
        }
    }

    public void setStatus(int issueId, boolean openIssue) {

        issueRepository.getById(issueId).setStatus(openIssue);
    }
}
