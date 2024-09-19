import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

class Solution {
  static final Map<Character, BinaryOperator<Integer>> OPERATOR_TO_FUNCTION =
      Map.ofEntries(
          entry('+', (x, y) -> x + y), entry('-', (x, y) -> x - y), entry('*', (x, y) -> x * y));

  public List<Integer> diffWaysToCompute(String expression) {
    List<Integer> values = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    parse(expression, values, operators);

    @SuppressWarnings("unchecked")
    List<Integer>[][] dp = new List[values.size()][values.size()];
    for (int i = 0; i < values.size(); ++i) {
      for (int j = 0; j < values.size(); ++j) {
        dp[i][j] = new ArrayList<>();
      }
    }
    for (int i = 0; i < values.size(); ++i) {
      dp[i][i].add(values.get(i));
    }

    for (int length = 2; length <= values.size(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= values.size(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        for (int k = beginIndex; k < endIndex; ++k) {
          dp[beginIndex][endIndex].addAll(
              merge(dp[beginIndex][k], dp[k + 1][endIndex], operators.get(k)));
        }
      }
    }

    return dp[0][values.size() - 1];
  }

  List<Integer> merge(List<Integer> operands1, List<Integer> operands2, char operator) {
    return operands1.stream()
        .flatMap(
            operand1 ->
                operands2.stream()
                    .map(operand2 -> OPERATOR_TO_FUNCTION.get(operator).apply(operand1, operand2)))
        .toList();
  }

  void parse(String expression, List<Integer> values, List<Character> operators) {
    int value = 0;
    for (int i = 0; i <= expression.length(); ++i) {
      if (i != expression.length() && Character.isDigit(expression.charAt(i))) {
        value = value * 10 + (expression.charAt(i) - '0');
      } else {
        values.add(value);
        value = 0;

        if (i != expression.length()) {
          operators.add(expression.charAt(i));
        }
      }
    }
  }
}
