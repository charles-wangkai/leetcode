import java.util.Stack;

class MyQueue {
  Stack<Integer> backStack = new Stack<>();
  Stack<Integer> frontStack = new Stack<>();

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
    return backStack.empty() && frontStack.empty();
  }

  private void checkAndMove() {
    if (frontStack.empty()) {
      while (!backStack.empty()) {
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
