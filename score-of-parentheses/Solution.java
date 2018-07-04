import java.util.Stack;

public class Solution {
	public int scoreOfParentheses(String S) {
		Stack<Integer> stack = new Stack<>();
		for (char ch : S.toCharArray()) {
			if (ch == '(') {
				stack.push(0);
			} else {
				int top = stack.pop();
				if (top == 0) {
					stack.push(1);
				} else {
					stack.pop();
					stack.push(top * 2);
				}

				if (stack.size() >= 2 && stack.get(stack.size() - 2) > 0) {
					int top1 = stack.pop();
					int top2 = stack.pop();
					stack.push(top1 + top2);
				}
			}
		}
		return stack.peek();
	}
}
