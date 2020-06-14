import java.util.Stack;

class Solution {
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();

        int[] result = new int[prices.length];
        for (int i = result.length - 1; i >= 0; --i) {
            while (!stack.empty() && stack.peek() > prices[i]) {
                stack.pop();
            }

            result[i] = prices[i] - (stack.empty() ? 0 : stack.peek());

            stack.push(prices[i]);
        }

        return result;
    }
}