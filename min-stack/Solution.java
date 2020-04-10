import java.util.Stack;

class MinStack {
	private Stack<Integer> values = new Stack<>();
	private Stack<Integer> mins = new Stack<>();

	public void push(int x) {
		values.push(x);

		if (mins.empty() || x <= mins.peek()) {
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