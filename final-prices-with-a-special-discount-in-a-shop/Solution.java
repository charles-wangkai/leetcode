import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int[] finalPrices(int[] prices) {
    Deque<Integer> stack = new ArrayDeque<>();

    int[] result = new int[prices.length];
    for (int i = result.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && stack.peek() > prices[i]) {
        stack.pop();
      }

      result[i] = prices[i] - (stack.isEmpty() ? 0 : stack.peek());

      stack.push(prices[i]);
    }

    return result;
  }
}
