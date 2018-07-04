import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public List<String> removeInvalidParentheses(String s) {
		return new ArrayList<String>(removeRightParentheses(s).stream()
				.flatMap(str -> removeRightParentheses(inverse(str)).stream().map(this::inverse))
				.collect(Collectors.toSet()));
	}

	String inverse(String s) {
		return new StringBuilder(s).reverse().chars().mapToObj(ch -> ch == '(' ? ')' : (ch == ')' ? '(' : (char) ch))
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	Set<String> removeRightParentheses(String s) {
		Set<String> result = new HashSet<String>();
		search(result, s, 0, 0, "");
		return result;
	}

	void search(Set<String> result, String s, int index, int leftRightDiff, String current) {
		if (index == s.length()) {
			result.add(current);
			return;
		}

		char ch = s.charAt(index);
		String next = current + ch;
		if (ch == '(') {
			search(result, s, index + 1, leftRightDiff + 1, next);
		} else if (ch == ')') {
			if (leftRightDiff > 0) {
				search(result, s, index + 1, leftRightDiff - 1, next);
			} else {
				for (int i = 0; i < next.length(); i++) {
					if (next.charAt(i) == ')') {
						search(result, s, index + 1, leftRightDiff, next.substring(0, i) + next.substring(i + 1));
					}
				}
			}
		} else {
			search(result, s, index + 1, leftRightDiff, next);
		}
	}
}
