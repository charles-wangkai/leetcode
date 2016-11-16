import java.util.Stack;

public class P_132Pattern {
	public boolean find132pattern(int[] nums) {
		Stack<Range> stack = new Stack<Range>();
		for (int num : nums) {
			Range current = new Range(num, num);
			while (!stack.empty() && current.max > stack.peek().min) {
				current.min = Math.min(current.min, stack.peek().min);
				current.max = Math.max(current.max, stack.peek().max);

				stack.pop();
			}
			stack.push(current);

			if (num > current.min && num < current.max) {
				return true;
			}
		}
		return false;
	}
}

class Range {
	int min;
	int max;

	Range(int min, int max) {
		this.min = min;
		this.max = max;
	}
}