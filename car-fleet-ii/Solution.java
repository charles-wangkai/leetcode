import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public double[] getCollisionTimes(int[][] cars) {
    double[] result = new double[cars.length];
    result[result.length - 1] = -1;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = result.length - 1; i >= 0; --i) {
      while (!stack.isEmpty()
          && (cars[stack.peek()][1] >= cars[i][1]
              || (result[stack.peek()] >= 0
                  && (double) (cars[stack.peek()][0] - cars[i][0])
                          / (cars[i][1] - cars[stack.peek()][1])
                      > result[stack.peek()]))) {
        stack.pop();
      }

      result[i] =
          stack.isEmpty()
              ? -1
              : ((double) (cars[stack.peek()][0] - cars[i][0])
                  / (cars[i][1] - cars[stack.peek()][1]));

      stack.push(i);
    }

    return result;
  }
}
