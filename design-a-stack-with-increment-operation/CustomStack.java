import java.util.ArrayList;
import java.util.List;

class CustomStack {
    private int maxSize;
    private List<Integer> values = new ArrayList<>();
    private List<Integer> deltas = new ArrayList<>();

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (values.size() == maxSize) {
            return;
        }

        values.add(x);
        deltas.add(0);
    }

    public int pop() {
        if (values.isEmpty()) {
            return -1;
        }

        int value = values.remove(values.size() - 1);
        int delta = deltas.remove(deltas.size() - 1);
        if (!deltas.isEmpty()) {
            deltas.set(deltas.size() - 1, deltas.get(deltas.size() - 1) + delta);
        }

        return value + delta;
    }

    public void increment(int k, int val) {
        if (!deltas.isEmpty()) {
            int index = Math.min(deltas.size() - 1, k - 1);
            deltas.set(index, deltas.get(index) + val);
        }
    }
}

// Your CustomStack object will be instantiated and called as such:
// CustomStack obj = new CustomStack(maxSize);
// obj.push(x);
// int param_2 = obj.pop();
// obj.increment(k,val);