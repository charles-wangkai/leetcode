import java.util.Stack;

public class Solution {
	public boolean isValid(String s) {
		Stack<Character> leftBrackets = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(' || ch == '[' || ch == '{') {
				leftBrackets.push(ch);
			} else {
				if (leftBrackets.empty()) {
					return false;
				}
				char leftBracket = leftBrackets.pop();
				if ((ch == ')' && leftBracket != '(')
						|| (ch == ']' && leftBracket != '[')
						|| (ch == '}' && leftBracket != '{')) {
					return false;
				}
			}
		}
		return leftBrackets.empty();
	}
}
