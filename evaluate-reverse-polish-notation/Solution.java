import static java.util.Map.entry;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

class Solution {
  static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATOR_TO_CALCULATOR =
      Map.ofEntries(
          entry("+", (x, y) -> x + y),
          entry("-", (x, y) -> x - y),
          entry("*", (x, y) -> x * y),
          entry("/", (x, y) -> x / y));

  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      if (OPERATOR_TO_CALCULATOR.containsKey(token)) {
        int operand2 = stack.pop();
        int operand1 = stack.pop();
        stack.push(OPERATOR_TO_CALCULATOR.get(token).apply(operand1, operand2));
      } else {
        stack.push(Integer.parseInt(token));
      }
    }

    return stack.peek();
  }
}
