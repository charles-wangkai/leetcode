import java.util.Stack;

public class BasicCalculator {
	public int calculate(String s) {
		Stack<Integer> factors = new Stack<Integer>();
		factors.push(1);
		int sign = 1;
		int result = 0;

		Expression expr = new Expression(s);
		String token;
		while ((token = expr.readNext()) != null) {
			if (token.equals("(")) {
				factors.push(factors.peek() * sign);
				sign = 1;
			} else if (token.equals(")")) {
				factors.pop();
			} else if (token.equals("+")) {
				sign = 1;
			} else if (token.equals("-")) {
				sign = -1;
			} else {
				result += factors.peek() * sign * Integer.parseInt(token);
			}
		}

		return result;
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
		if (ch == '(' || ch == ')' || ch == '+' || ch == '-') {
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