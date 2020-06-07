import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
    private List<String> history = new ArrayList<>();
    private int index = 0;

    public BrowserHistory(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        while (history.size() != index + 1) {
            history.remove(history.size() - 1);
        }

        history.add(url);
        ++index;
    }

    public String back(int steps) {
        index = Math.max(0, index - steps);

        return history.get(index);
    }

    public String forward(int steps) {
        index = Math.min(history.size() - 1, index + steps);

        return history.get(index);
    }
}

// Your BrowserHistory object will be instantiated and called as such:
// BrowserHistory obj = new BrowserHistory(homepage);
// obj.visit(url);
// String param_2 = obj.back(steps);
// String param_3 = obj.forward(steps);