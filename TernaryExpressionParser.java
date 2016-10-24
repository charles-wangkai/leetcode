public class TernaryExpressionParser {
	public String parseTernary(String expression) {
		return String.valueOf(evaluate(expression, 0).value);
	}

	Result evaluate(String expression, int index) {
		if (index + 1 < expression.length() && expression.charAt(index + 1) == '?') {
			Result firstResult = evaluate(expression, index + 2);
			Result secondResult = evaluate(expression, firstResult.endIndex + 2);

			if (expression.charAt(index) == 'T') {
				return new Result(firstResult.value, secondResult.endIndex);
			} else {
				return secondResult;
			}
		} else {
			return new Result(expression.charAt(index), index);
		}
	}
}

class Result {
	char value;
	int endIndex;

	Result(char value, int endIndex) {
		this.value = value;
		this.endIndex = endIndex;
	}
}