import java.util.Stack;

public class Solution {
	Stack<Integer> numbers = new Stack<Integer>();
	Stack<Integer> mins = new Stack<Integer>();

	public void push(int x) {
		numbers.push(x);
		if (mins.empty() || x <= mins.peek()) {
			mins.push(x);
		}
	}

	public void pop() {
		int number = numbers.pop();
		if (mins.peek() == number) {
			mins.pop();
		}
	}

	public int top() {
		return numbers.peek();
	}

	public int getMin() {
		return mins.peek();
	}
}
