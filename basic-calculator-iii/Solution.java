public class Solution {
	public int calculate(String s) {
		long result = 0;
		int sign = 1;
		long term = 1;

		Expression expr = new Expression(s);
		String token;
		while ((token = expr.readNext()) != null) {
			if (token.equals("+") || token.equals("-")) {
				result += sign * term;
				term = 1;
				sign = token.equals("+") ? 1 : -1;
			} else if (token.equals("*") || token.equals("/")) {
				String t = expr.readNext();
				long nextNumber = readNumber(s, t, expr);
				if (token.equals("*")) {
					term *= nextNumber;
				} else {
					term /= nextNumber;
				}
			} else {
				term = readNumber(s, token, expr);
			}
		}
		result += sign * term;

		return (int) result;
	}

	long readNumber(String s, String token, Expression expr) {
		if (token.equals("(")) {
			int beginIndex = expr.index;
			int depth = 1;
			while (depth != 0) {
				String t = expr.readNext();
				if (t.equals("(")) {
					depth++;
				} else if (t.equals(")")) {
					depth--;
				}
			}
			int endIndex = expr.index - 1;

			return calculate(s.substring(beginIndex, endIndex));
		} else {
			return Long.parseLong(token);
		}
	}
}

class Expression {
	String str;
	int index = 0;

	public Expression(String str) {
		this.str = str;
	}

	public String readNext() {
		while (index < str.length() && str.charAt(index) == ' ') {
			index++;
		}

		if (index == str.length()) {
			return null;
		}

		char ch = str.charAt(index);
		if (ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
			index++;
			return ch + "";
		}

		int nextIndex = index + 1;
		while (nextIndex < str.length() && Character.isDigit(str.charAt(nextIndex))) {
			nextIndex++;
		}
		String number = str.substring(index, nextIndex);
		index = nextIndex;
		return number;
	}
}