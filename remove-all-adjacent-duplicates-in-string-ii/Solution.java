import java.util.Stack;

public class Solution {
	public String removeDuplicates(String s, int k) {
		Stack<Element> stack = new Stack<>();
		for (char letter : s.toCharArray()) {
			if (!stack.empty() && stack.peek().letter == letter) {
				stack.peek().count += 1;

				if (stack.peek().count == k) {
					stack.pop();
				}
			} else {
				stack.push(new Element(letter, 1));
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.empty()) {
			Element element = stack.pop();
			for (int i = 0; i < element.count; i++) {
				sb.append(element.letter);
			}
		}

		return sb.reverse().toString();
	}
}

class Element {
	char letter;
	int count;

	Element(char letter, int count) {
		this.letter = letter;
		this.count = count;
	}
}