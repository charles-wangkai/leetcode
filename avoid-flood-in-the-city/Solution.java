import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
  public int[] avoidFlood(int[] rains) {
    int[] nextIndices = new int[rains.length];
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    for (int i = nextIndices.length - 1; i >= 0; --i) {
      nextIndices[i] = valueToIndex.getOrDefault(rains[i], -1);
      valueToIndex.put(rains[i], i);
    }

    int[] result = new int[rains.length];
    Set<Integer> waters = new HashSet<>();
    PriorityQueue<Integer> nexts = new PriorityQueue<>();
    for (int i = 0; i < result.length; ++i) {
      if (rains[i] == 0) {
        if (nexts.isEmpty()) {
          result[i] = 1;
        } else {
          result[i] = rains[nexts.poll()];
          waters.remove(result[i]);
        }
      } else {
        if (waters.contains(rains[i])) {
          return new int[0];
        }

        result[i] = -1;
        waters.add(rains[i]);

        if (nextIndices[i] != -1) {
          nexts.add(nextIndices[i]);
        }
      }
    }

    return result;
  }
}