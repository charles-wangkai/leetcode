import java.util.Stack;

public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String token : tokens) {
			if (token.equals("+") || token.equals("-") || token.equals("*")
					|| token.equals("/")) {
				int operand2 = stack.pop();
				int operand1 = stack.pop();
				int result;
				if (token.equals("+")) {
					result = operand1 + operand2;
				} else if (token.equals("-")) {
					result = operand1 - operand2;
				} else if (token.equals("*")) {
					result = operand1 * operand2;
				} else {
					result = operand1 / operand2;
				}
				stack.push(result);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.peek();
	}
}
