import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class Solution {
  public int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (int asteroid : asteroids) {
      if (asteroid > 0) {
        stack.push(asteroid);
      } else {
        while (true) {
          if (stack.isEmpty() || stack.peek() < 0) {
            stack.push(asteroid);

            break;
          }
          if (stack.peek() + asteroid > 0) {
            break;
          }

          int top = stack.pop();
          if (top + asteroid == 0) {
            break;
          }
        }
      }
    }

    List<Integer> result = new ArrayList<>(stack);
    Collections.reverse(result);

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
