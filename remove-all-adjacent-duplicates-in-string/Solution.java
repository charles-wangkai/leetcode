import java.util.Stack;

public class Solution {
	public String removeDuplicates(String S) {
		Stack<Character> stack = new Stack<>();
		for (char letter : S.toCharArray()) {
			if (!stack.empty() && stack.peek() == letter) {
				stack.pop();
			} else {
				stack.push(letter);
			}
		}

		StringBuilder result = new StringBuilder();
		while (!stack.empty()) {
			result.append(stack.pop());
		}
		return result.reverse().toString();
	}
}
