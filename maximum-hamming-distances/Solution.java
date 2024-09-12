import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  public int[] maxHammingDistances(int[] nums, int m) {
    int[] distances = new int[1 << m];
    Arrays.fill(distances, -1);

    Queue<Integer> queue = new ArrayDeque<>();

    for (int num : nums) {
      distances[num] = 0;
      queue.offer(num);
    }

    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int b = 0; b < m; ++b) {
        int adj = head ^ (1 << b);
        if (distances[adj] == -1) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    return Arrays.stream(nums).map(num -> m - distances[num ^ ((1 << m) - 1)]).toArray();
  }
}