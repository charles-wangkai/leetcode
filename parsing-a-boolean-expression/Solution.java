import java.util.ArrayList;
import java.util.List;

class Solution {
  public boolean parseBoolExpr(String expression) {
    char symbol = expression.charAt(0);
    if (symbol == 't') {
      return true;
    }
    if (symbol == 'f') {
      return false;
    }
    if (symbol == '!') {
      return !parseBoolExpr(expression.substring(2, expression.length() - 1));
    }

    List<Boolean> operands = new ArrayList<>();
    int beginIndex = 2;
    int depth = 0;
    for (int i = 2; i <= expression.length() - 1; ++i) {
      char c = expression.charAt(i);
      if (i == expression.length() - 1 || (c == ',' && depth == 0)) {
        operands.add(parseBoolExpr(expression.substring(beginIndex, i)));
        beginIndex = i + 1;
      } else if (c == '(') {
        ++depth;
      } else if (c == ')') {
        --depth;
      }
    }

    return (symbol == '&')
        ? operands.stream().allMatch(part -> part)
        : operands.stream().anyMatch(part -> part);
  }
}
