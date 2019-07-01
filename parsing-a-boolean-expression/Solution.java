import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean parseBoolExpr(String expression) {
		char first = expression.charAt(0);

		if (first == 't') {
			return true;
		} else if (first == 'f') {
			return false;
		} else if (first == '!') {
			return !parseBoolExpr(expression.substring(2, expression.length() - 1));
		}

		List<Boolean> parts = new ArrayList<>();
		int beginIndex = 2;
		int depth = 0;
		for (int i = 2; i <= expression.length() - 1; i++) {
			char ch = expression.charAt(i);

			if (i == expression.length() - 1 || (ch == ',' && depth == 0)) {
				parts.add(parseBoolExpr(expression.substring(beginIndex, i)));

				beginIndex = i + 1;
			} else if (ch == '(') {
				depth++;
			} else if (ch == ')') {
				depth--;
			}
		}

		if (first == '&') {
			return parts.stream().allMatch(x -> x);
		} else {
			return parts.stream().anyMatch(x -> x);
		}
	}
}
