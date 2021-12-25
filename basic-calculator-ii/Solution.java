class Solution {
  public int calculate(String s) {
    int result = 0;
    int sign = 1;
    int term = -1;

    Expression expr = new Expression(s);
    while (true) {
      String token = expr.readNext();
      if (token == null) {
        break;
      }

      if (token.equals("+") || token.equals("-")) {
        result += sign * term;
        sign = token.equals("+") ? 1 : -1;
      } else if (token.equals("*") || token.equals("/")) {
        int nextNumber = Integer.parseInt(expr.readNext());
        if (token.equals("*")) {
          term *= nextNumber;
        } else {
          term /= nextNumber;
        }
      } else {
        term = Integer.parseInt(token);
      }
    }
    result += sign * term;

    return result;
  }
}

class Expression {
  String str;
  int index = 0;

  Expression(String str) {
    this.str = str;
  }

  String readNext() {
    while (index != str.length() && str.charAt(index) == ' ') {
      ++index;
    }

    if (index == str.length()) {
      return null;
    }

    char ch = str.charAt(index);
    if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
      ++index;

      return String.valueOf(ch);
    }

    int nextIndex = index + 1;
    while (nextIndex != str.length() && Character.isDigit(str.charAt(nextIndex))) {
      ++nextIndex;
    }
    String number = str.substring(index, nextIndex);
    index = nextIndex;

    return number;
  }
}
