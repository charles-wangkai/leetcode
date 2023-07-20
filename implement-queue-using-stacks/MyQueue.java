import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
  Deque<Integer> backStack = new ArrayDeque<>();
  Deque<Integer> frontStack = new ArrayDeque<>();

  public void push(int x) {
    backStack.push(x);
  }

  public int pop() {
    checkAndMove();

    return frontStack.pop();
  }

  public int peek() {
    checkAndMove();

    return frontStack.peek();
  }

  public boolean empty() {
    return backStack.isEmpty() && frontStack.isEmpty();
  }

  private void checkAndMove() {
    if (frontStack.isEmpty()) {
      while (!backStack.isEmpty()) {
        frontStack.push(backStack.pop());
      }
    }
  }
}

// Your MyQueue object will be instantiated and called as such:
// MyQueue obj = new MyQueue();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.peek();
// boolean param_4 = obj.empty();
