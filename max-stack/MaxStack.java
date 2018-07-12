import java.util.ArrayList;
import java.util.List;

public class MaxStack {
	/** initialize your data structure here. */
	private List<Integer> values;

	public MaxStack() {
		values = new ArrayList<Integer>();
	}

	public void push(int x) {
		values.add(x);
	}

	public int pop() {
		return values.remove(values.size() - 1);
	}

	public int top() {
		return values.get(values.size() - 1);
	}

	public int peekMax() {
		return values.stream().mapToInt(x -> x).max().getAsInt();
	}

	public int popMax() {
		int index = 0;
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) >= values.get(index)) {
				index = i;
			}
		}
		return values.remove(index);
	}
}

// Your MaxStack object will be instantiated and called as such:
// MaxStack obj = new MaxStack();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.peekMax();
// int param_5 = obj.popMax();
