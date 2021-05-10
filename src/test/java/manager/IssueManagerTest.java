package manager;

import data.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    @Nested
    class IssueManagerWhenOneIssueTest {
        IssueManager issueManager = new IssueManager();
        Issue firstIssue = new Issue(1, "Иванов", "Label1", "Smith");

        @BeforeEach
        public void setUp() {
            issueManager.addIssue(firstIssue);
        }

        @Test
        public void shouldGetOpenedIssueList() {
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.getOpenedIssueList().size()];

            issueManager.getOpenedIssueList().toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldGetClosedIssueList() {
            Issue[] expected = new Issue[] {firstIssue};

            issueManager.setStatus(1, false);
            Issue[] actual = new Issue[issueManager.getClosedIssueList().size()];

            issueManager.getClosedIssueList().toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAuthor() {
            Predicate<Issue> p = issue -> issue.getAuthor().equalsIgnoreCase("Иванов");
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByLabel() {
            Predicate<Issue> p = issue -> issue.getLabel().equalsIgnoreCase("Label1");
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAssignee() {
            Predicate<Issue> p = issue -> issue.getAssignee().equalsIgnoreCase("Smith");
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }
    }

    @Nested
    class IssueManagerWhenSeveralIssuesTest {
        IssueManager issueManager = new IssueManager();
        Issue firstIssue = new Issue(1, "Иванов", "Label1", "Smith");
        Issue secondIssue = new Issue(2, "Иванов", "Label2", "Anderson");
        Issue thirdIssue = new Issue(3, "Петров", "Label1", "Anderson");

        @BeforeEach
        public void setUp() {
            issueManager.addIssue(firstIssue);
            issueManager.addIssue(secondIssue);
            issueManager.addIssue(thirdIssue);
        }

        @Test
        public void shouldGetOpenedIssueList() {
            Issue[] expected = new Issue[] {firstIssue, secondIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.getOpenedIssueList().size()];

            issueManager.getOpenedIssueList().toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldGetClosedIssueList() {
            Issue[] expected = new Issue[] {secondIssue};

            issueManager.setStatus(2, false);
            Issue[] actual = new Issue[issueManager.getClosedIssueList().size()];

            issueManager.getClosedIssueList().toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAuthor() {
            Predicate<Issue> p = issue -> issue.getAuthor().equalsIgnoreCase("Иванов");
            Issue[] expected = new Issue[] {firstIssue, secondIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByLabel() {
            Predicate<Issue> p = issue -> issue.getLabel().equalsIgnoreCase("Label1");
            Issue[] expected = new Issue[] {firstIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAssignee() {
            Predicate<Issue> p = issue -> issue.getAssignee().equalsIgnoreCase("Anderson");
            Issue[] expected = new Issue[] {secondIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.filterBy(p).size()];

            issueManager.filterBy(p).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldSortIssuesNewestToOldest() {
            Issue[] expected = new Issue[] {thirdIssue, secondIssue, firstIssue};
            Issue[] actual = new Issue[issueManager.getAll().size()];

            issueManager.sortIssues(true);
            issueManager.getAll().toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldSortIssuesOldestToNewest() {
            Issue[] expected = new Issue[] {firstIssue, secondIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.getAll().size()];

            issueManager.sortIssues(false);
            issueManager.getAll().toArray(actual);
            assertArrayEquals(expected, actual);
        }
    }
}