package manager;

import data.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    @Nested
    class IssueManagerWhenOneIssueTest {
        IssueManager issueManager = new IssueManager();
        Set<String> label1 = new HashSet<>(Arrays.asList("Label11", "Label12"));

        Issue firstIssue = new Issue(1, "Иванов", label1, "Smith");

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
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterByAuthor("Иванов").size()];

            issueManager.filterByAuthor("Иванов").toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByLabel() {
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterByLabel("Label12").size()];

            issueManager.filterByLabel("Label12").toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAssignee() {
            Issue[] expected = new Issue[] {firstIssue};
            Issue[] actual = new Issue[issueManager.filterByAssignee("Smith").size()];

            issueManager.filterByAssignee("Smith").toArray(actual);
            assertArrayEquals(expected, actual);
        }
    }

    @Nested
    class IssueManagerWhenSeveralIssuesTest {
        IssueManager issueManager = new IssueManager();
        Set<String> label1 = new HashSet<>(Arrays.asList("Label11", "Label12"));
        Set<String> label2 = new HashSet<>(Arrays.asList("Label2"));
        Set<String> label3 = new HashSet<>(Arrays.asList("Label11", "Label3"));

        Issue firstIssue = new Issue(1, "Иванов", label1, "Smith");
        Issue secondIssue = new Issue(2, "Иванов", label2, "Anderson");
        Issue thirdIssue = new Issue(3, "Петров", label3, "Anderson");

        @BeforeEach
        public void setUp() throws InterruptedException {
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
            Issue[] expected = new Issue[] {firstIssue, secondIssue};
            Issue[] actual = new Issue[issueManager.filterByAuthor("Иванов").size()];

            issueManager.filterByAuthor("Иванов").toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByLabel() {
            Issue[] expected = new Issue[] {firstIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.filterByLabel("Label11").size()];

            issueManager.filterByLabel("Label11").toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFilterByAssignee() {
            Issue[] expected = new Issue[] {secondIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.filterByAssignee("Anderson").size()];

            issueManager.filterByAssignee("Anderson").toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldSortIssuesNewestToOldest() {
            Issue[] expected = new Issue[] {thirdIssue, secondIssue, firstIssue};
            Issue[] actual = new Issue[ issueManager.sortIssues(true).size()];

            issueManager.sortIssues(true).toArray(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldSortIssuesOldestToNewest() {
            Issue[] expected = new Issue[] {firstIssue, secondIssue, thirdIssue};
            Issue[] actual = new Issue[issueManager.sortIssues(false).size()];

            issueManager.sortIssues(false).toArray(actual);
            assertArrayEquals(expected, actual);
        }
    }
}