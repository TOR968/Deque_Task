package codefinity.Task;

import java.util.Deque;

public class BrowserHistoryImpl implements BrowserHistory {
    private Deque<String> backStack;
    private Deque<String> forwardStack;
    private String currentUrl;

    public BrowserHistoryImpl(Deque<String> backStack, Deque<String> forwardStack, String currentUrl) {
        this.backStack = backStack;
        this.forwardStack = forwardStack;
        this.currentUrl = currentUrl;
    }

    @Override
    public void visitPage(String url) {
        backStack.push(currentUrl);
        forwardStack.clear();
        currentUrl = url;
    }

    @Override
    public void goBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentUrl);
            currentUrl = backStack.pop();
        } else {
            System.out.println("Cannot go back. Already at the beginning.");
        }
    }

    @Override
    public void goForward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentUrl);
            currentUrl = forwardStack.pop();
        } else {
            System.out.println("Cannot go forward. Already at the latest page.");
        }
    }

    @Override
    public String getCurrentPage() {
        return currentUrl;
    }
}
