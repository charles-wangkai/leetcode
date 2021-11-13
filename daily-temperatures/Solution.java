import java.util.Stack;

class Solution {
  public int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Stack<Integer> indices = new Stack<>();
    for (int i = 0; i < temperatures.length; ++i) {
      while (!indices.empty() && temperatures[i] > temperatures[indices.peek()]) {
        int index = indices.pop();
        result[index] = i - index;
      }
      indices.push(i);
    }

    return result;
  }
}
