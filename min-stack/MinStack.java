import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
  private Deque<Integer> values = new ArrayDeque<>();
  private Deque<Integer> mins = new ArrayDeque<>();

  public void push(int x) {
    values.push(x);

    if (mins.isEmpty() || x <= mins.peek()) {
      mins.push(x);
    }
  }

  public void pop() {
    int value = values.pop();

    if (mins.peek() == value) {
      mins.pop();
    }
  }

  public int top() {
    return values.peek();
  }

  public int getMin() {
    return mins.peek();
  }
}

// Your MinStack object will be instantiated and called as such:
// MinStack obj = new MinStack();
// obj.push(x);
// obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.getMin();
