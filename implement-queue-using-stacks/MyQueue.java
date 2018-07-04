import java.util.Stack;

// MyQueue
public class MyQueue {
	Stack<Integer> stackBack = new Stack<Integer>();
	Stack<Integer> stackFront = new Stack<Integer>();

	// Push element x to the back of queue.
	public void push(int x) {
		stackBack.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		checkAndMove();
		stackFront.pop();
	}

	// Get the front element.
	public int peek() {
		checkAndMove();
		return stackFront.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return stackBack.empty() && stackFront.empty();
	}

	private void checkAndMove() {
		if (stackFront.empty()) {
			while (!stackBack.empty()) {
				stackFront.push(stackBack.pop());
			}
		}
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */