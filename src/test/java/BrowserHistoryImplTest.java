import codefinity.Task.BrowserHistory;
import codefinity.Task.BrowserHistoryImpl;
import org.junit.*;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class BrowserHistoryImplTest {
    private BrowserHistory browserHistory;

    @Before
    public void setUp() {
        Deque<String> backStack = new LinkedList<>();
        Deque<String> forwardStack = new LinkedList<>();
        String initialUrl = "https://codefinity.com/profile/my-home";
        browserHistory = new BrowserHistoryImpl(backStack, forwardStack, initialUrl);
    }

    @Test
    public void testVisitPage() {
        String newUrl = "https://codefinity.com/profile/settings";
        browserHistory.visitPage(newUrl);
        assertEquals(newUrl, browserHistory.getCurrentPage());
    }

    @Test
    public void testGoBack() {
        // Test going back when there's a page in the backStack
        String newUrl1 = "https://codefinity.com/page1";
        String newUrl2 = "https://codefinity.com/page2";
        browserHistory.visitPage(newUrl1);
        browserHistory.visitPage(newUrl2);

        browserHistory.goBack();
        assertEquals(newUrl1, browserHistory.getCurrentPage());
    }

    @Test
    public void testGoBackAtBeginning() {
        // Test trying to go back when already at the beginning
        browserHistory.goBack();
        assertEquals("https://codefinity.com/profile/my-home", browserHistory.getCurrentPage());
    }

    @Test
    public void testGoForward() {
        // Test going forward when there's a page in the forwardStack
        String newUrl1 = "https://codefinity.com/page1";
        String newUrl2 = "https://codefinity.com/page2";
        browserHistory.visitPage(newUrl1);
        browserHistory.visitPage(newUrl2);

        browserHistory.goBack();
        browserHistory.goForward();
        assertEquals(newUrl2, browserHistory.getCurrentPage());
    }

    @Test
    public void testGoForwardAtLatestPage() {
        // Test trying to go forward when already at the latest page
        browserHistory.goForward();
        assertEquals("https://codefinity.com/profile/my-home", browserHistory.getCurrentPage());
    }
}
