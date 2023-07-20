import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < temperatures.length; ++i) {
      while (!indices.isEmpty() && temperatures[i] > temperatures[indices.peek()]) {
        int index = indices.pop();
        result[index] = i - index;
      }
      indices.push(i);
    }

    return result;
  }
}
