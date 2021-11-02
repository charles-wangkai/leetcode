import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int LIMIT = 1000;
  static final Operation[] OPERATIONS = {(x, y) -> x + y, (x, y) -> x - y, (x, y) -> x ^ y};

  public int minimumOperations(int[] nums, int start, int goal) {
    int[] distances = new int[LIMIT + 1];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[start] = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int num : nums) {
        for (Operation operation : OPERATIONS) {
          int next = operation.apply(head, num);
          if (next == goal) {
            return distances[head] + 1;
          }
          if (next >= 0 && next <= LIMIT && distances[next] == Integer.MAX_VALUE) {
            distances[next] = distances[head] + 1;
            queue.offer(next);
          }
        }
      }
    }

    return -1;
  }
}

interface Operation {
  int apply(int x, int y);
}
