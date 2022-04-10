class Solution {
  public String minimizeResult(String expression) {
    int plusIndex = expression.indexOf('+');

    int minValue = Integer.MAX_VALUE;
    String result = null;
    for (int leftIndex = 0; leftIndex < plusIndex; ++leftIndex) {
      for (int rightIndex = plusIndex + 1; rightIndex < expression.length(); ++rightIndex) {
        int value =
            (Integer.parseInt(expression.substring(leftIndex, plusIndex))
                    + Integer.parseInt(expression.substring(plusIndex + 1, rightIndex + 1)))
                * ((leftIndex == 0) ? 1 : Integer.parseInt(expression.substring(0, leftIndex)))
                * ((rightIndex == expression.length() - 1)
                    ? 1
                    : Integer.parseInt(expression.substring(rightIndex + 1)));
        if (value < minValue) {
          minValue = value;
          result =
              String.format(
                  "%s(%s)%s",
                  expression.substring(0, leftIndex),
                  expression.substring(leftIndex, rightIndex + 1),
                  expression.substring(rightIndex + 1));
        }
      }
    }

    return result;
  }
}