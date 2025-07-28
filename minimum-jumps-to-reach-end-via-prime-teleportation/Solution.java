import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  public int minJumps(int[] nums) {
    int maxValue = Arrays.stream(nums).max().getAsInt();

    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int[] distances = new int[nums.length];
    Arrays.fill(distances, -1);
    distances[0] = 0;

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);

    Set<Integer> seen = new HashSet<>();
    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int offset : new int[] {-1, 1}) {
        int next = head + offset;
        if (next >= 0 && next < distances.length && distances[next] == -1) {
          distances[next] = distances[head] + 1;
          queue.offer(next);
        }
      }

      if (!seen.contains(nums[head]) && isPrime(nums[head])) {
        seen.add(nums[head]);

        for (int value = nums[head]; value <= maxValue; value += nums[head]) {
          for (int index : valueToIndices.getOrDefault(value, List.of())) {
            if (distances[index] == -1) {
              distances[index] = distances[head] + 1;
              queue.offer(index);
            }
          }
        }
      }
    }

    return distances[distances.length - 1];
  }

  boolean isPrime(int x) {
    if (x < 2) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}