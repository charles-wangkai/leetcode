import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public int evaluate(String expression) {
    return eval(expression, new HashMap<>());
  }

  int eval(String expression, Map<String, Integer> variable2value) {
    variable2value = new HashMap<>(variable2value);

    char first = expression.charAt(0);
    if (first == '(') {
      List<String> tokens = parse(expression);
      String command = tokens.get(0);
      if (command.equals("add")) {
        return eval(tokens.get(1), variable2value) + eval(tokens.get(2), variable2value);
      } else if (command.equals("mult")) {
        return eval(tokens.get(1), variable2value) * eval(tokens.get(2), variable2value);
      } else {
        int tokenNum = tokens.size();
        for (int i = 1; i < tokenNum - 1; i += 2) {
          variable2value.put(tokens.get(i), eval(tokens.get(i + 1), variable2value));
        }
        return eval(tokens.get(tokenNum - 1), variable2value);
      }
    } else if (Character.isLowerCase(first)) {
      return variable2value.get(expression);
    } else {
      return Integer.parseInt(expression);
    }
  }

  List<String> parse(String expression) {
    List<String> tokens = new ArrayList<>();
    int beginIndex = 1;
    while (beginIndex < expression.length()) {
      int depth = 0;
      int endIndex = beginIndex;
      while (endIndex < expression.length() - 1) {
        char ch = expression.charAt(endIndex);

        if (ch == ' ' && depth == 0) {
          break;
        }

        if (ch == '(') {
          depth++;
        } else if (ch == ')') {
          depth--;
        }

        endIndex++;
      }

      tokens.add(expression.substring(beginIndex, endIndex));
      beginIndex = endIndex + 1;
    }
    return tokens;
  }
}
