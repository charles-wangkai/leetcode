// https://leetcode.com/problems/minimum-operations-to-make-xor-of-all-elements-zero/solutions/8511305/xor-state-bfs-on-u-d-by-crfmn-5p0j/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int minOperations(int[] nums) {
    int total = Arrays.stream(nums).reduce(0, (acc, x) -> acc ^ x);
    if (total == 0) {
      return 0;
    }

    int[] distinctValues = Arrays.stream(nums).distinct().toArray();
    if (distinctValues.length == 1) {
      return -1;
    }

    int distance = computeDistance(distinctValues, total);

    return (distance == nums.length) ? -1 : distance;
  }

  int computeDistance(int[] distinctValues, int target) {
    Map<Integer, Integer> valueToDistance = new HashMap<>();
    valueToDistance.put(0, 0);

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);

    while (true) {
      int head = queue.poll();
      if (head == target) {
        return valueToDistance.get(head);
      }

      for (int value : distinctValues) {
        int next = head ^ value;
        if (!valueToDistance.containsKey(next)) {
          valueToDistance.put(next, valueToDistance.get(head) + 1);
          queue.offer(next);
        }
      }
    }
  }
}