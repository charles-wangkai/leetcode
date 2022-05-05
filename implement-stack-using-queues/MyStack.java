import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {
  Queue<Integer> queue = new ArrayDeque<>();
  Queue<Integer> buffer = new ArrayDeque<>();
  int topElement;

  public void push(int x) {
    queue.offer(x);
    topElement = x;
  }

  public int pop() {
    int result = topElement;

    while (queue.size() >= 2) {
      buffer.offer(queue.poll());
    }
    queue.poll();

    while (!buffer.isEmpty()) {
      int element = buffer.poll();
      queue.offer(element);
      topElement = element;
    }

    return result;
  }

  public int top() {
    return topElement;
  }

  public boolean empty() {
    return queue.isEmpty();
  }
}

// Your MyStack object will be instantiated and called as such:
// MyStack obj = new MyStack();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.top();
// boolean param_4 = obj.empty();
