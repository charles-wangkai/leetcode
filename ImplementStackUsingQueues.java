import java.util.LinkedList;
import java.util.Queue;

class MyStack {
	Queue<Integer> queue;
	Queue<Integer> buffer;
	int topElement;

	MyStack() {
		queue = new LinkedList<Integer>();
		buffer = new LinkedList<Integer>();
	}

	// Push element x onto stack.
	public void push(int x) {
		queue.offer(x);
		topElement = x;
	}

	// Removes the element on top of the stack.
	public void pop() {
		while (queue.size() > 1) {
			buffer.offer(queue.poll());
		}
		queue.poll();

		while (!buffer.isEmpty()) {
			int element = buffer.poll();
			queue.offer(element);
			topElement = element;
		}
	}

	// Get the top element.
	public int top() {
		return topElement;
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return queue.isEmpty();
	}
}