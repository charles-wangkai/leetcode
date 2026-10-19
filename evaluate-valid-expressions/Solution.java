import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public long evaluateExpression(String expression) {
    String[] parts =
        Arrays.stream(expression.split("[(),]"))
            .filter(part -> !part.isEmpty())
            .toArray(String[]::new);

    Deque<Object> stack = new ArrayDeque<>();
    for (String part : parts) {
      if (part.equals("add") || part.equals("sub") || part.equals("mul") || part.equals("div")) {
        stack.push(part);
      } else {
        long value = Long.parseLong(part);
        while (!stack.isEmpty() && stack.peek() instanceof Long) {
          long operand1 = (long) stack.pop();
          String operator = (String) stack.pop();
          if (operator.equals("add")) {
            value = operand1 + value;
          } else if (operator.equals("sub")) {
            value = operand1 - value;
          } else if (operator.equals("mul")) {
            value = operand1 * value;
          } else {
            value = operand1 / value;
          }
        }

        stack.push(value);
      }
    }

    return (long) stack.peek();
  }
}