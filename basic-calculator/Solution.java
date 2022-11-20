import java.util.Stack;

class Solution {
  public int calculate(String s) {
    int result = 0;
    Stack<Integer> factors = new Stack<>();
    factors.push(1);
    int sign = 1;
    Expression expr = new Expression(s);
    while (true) {
      String token = expr.readNext();
      if (token == null) {
        break;
      }

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
    this.str = str.replace(" ", "");
  }

  public String readNext() {
    if (index == str.length()) {
      return null;
    }

    char c = str.charAt(index);
    if (c == '(' || c == ')' || c == '+' || c == '-') {
      ++index;

      return String.valueOf(c);
    }

    int nextIndex = index;
    while (nextIndex != str.length() && Character.isDigit(str.charAt(nextIndex))) {
      ++nextIndex;
    }
    String number = str.substring(index, nextIndex);
    index = nextIndex;

    return number;
  }
}
