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
				long nextNumber = Long.parseLong(expr.readNext());
				if (token.equals("*")) {
					term *= nextNumber;
				} else {
					term /= nextNumber;
				}
			} else {
				term = Long.parseLong(token);
			}
		}
		result += sign * term;

		return (int) result;
	}
}

class Expression {
	private String str;
	private int index = 0;

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
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
			index++;
			return ch + "";
		}

		int nextIndex = index + 1;
		while (nextIndex < str.length()
				&& Character.isDigit(str.charAt(nextIndex))) {
			nextIndex++;
		}
		String number = str.substring(index, nextIndex);
		index = nextIndex;
		return number;
	}
}