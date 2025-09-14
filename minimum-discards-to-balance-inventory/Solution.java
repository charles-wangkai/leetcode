import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
    int result = 0;
    Map<Integer, Integer> typeToCount = new HashMap<>();
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < arrivals.length; ++i) {
      if (!queue.isEmpty() && queue.peek() == i - w) {
        int type = arrivals[queue.poll()];
        typeToCount.put(type, typeToCount.get(type) - 1);
      }

      if (typeToCount.getOrDefault(arrivals[i], 0) == m) {
        ++result;
      } else {
        typeToCount.put(arrivals[i], typeToCount.getOrDefault(arrivals[i], 0) + 1);
        queue.offer(i);
      }
    }

    return result;
  }
}