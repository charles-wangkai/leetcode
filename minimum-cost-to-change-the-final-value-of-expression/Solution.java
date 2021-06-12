import java.util.Arrays;
import java.util.Stack;

class Solution {
  public int minOperationsToFlip(String expression) {
    int[] matchingIndices = new int[expression.length()];
    Arrays.fill(matchingIndices, -1);
    Stack<Integer> leftIndices = new Stack<>();
    for (int i = 0; i < expression.length(); ++i) {
      char ch = expression.charAt(i);
      if (ch == '(') {
        leftIndices.push(i);
      } else if (ch == ')') {
        int leftIndex = leftIndices.pop();
        matchingIndices[leftIndex] = i;
        matchingIndices[i] = leftIndex;
      }
    }

    return Arrays.stream(computeCosts(expression, matchingIndices, 0, expression.length() - 1))
        .max()
        .getAsInt();
  }

  int[] computeCosts(String expression, int[] matchingIndices, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      char ch = expression.charAt(beginIndex);

      return new int[] {(ch == '0') ? 0 : 1, (ch == '1') ? 0 : 1};
    }

    int rightBegin;
    if (expression.charAt(endIndex) == ')') {
      rightBegin = matchingIndices[endIndex];
    } else {
      rightBegin = endIndex;
    }

    if (rightBegin == beginIndex) {
      return computeCosts(expression, matchingIndices, beginIndex + 1, endIndex - 1);
    }

    int[] leftCosts = computeCosts(expression, matchingIndices, beginIndex, rightBegin - 2);
    char operator = expression.charAt(rightBegin - 1);
    int[] rightCosts = computeCosts(expression, matchingIndices, rightBegin, endIndex);

    int[] costs = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
    for (int left = 0; left <= 1; ++left) {
      for (int right = 0; right <= 1; ++right) {
        if (operator == '&') {
          costs[left & right] = Math.min(costs[left & right], leftCosts[left] + rightCosts[right]);
          costs[left | right] =
              Math.min(costs[left | right], leftCosts[left] + rightCosts[right] + 1);
        } else {
          costs[left | right] = Math.min(costs[left | right], leftCosts[left] + rightCosts[right]);
          costs[left & right] =
              Math.min(costs[left & right], leftCosts[left] + rightCosts[right] + 1);
        }
      }
    }

    return costs;
  }
}
