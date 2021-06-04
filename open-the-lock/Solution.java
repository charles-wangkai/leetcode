import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  static final String INITIAL_WHEEL = "0000";
  static final int[] OFFSETS = {-1, 1};

  public int openLock(String[] deadends, String target) {
    Set<String> deads = Arrays.stream(deadends).collect(Collectors.toSet());
    if (deads.contains(INITIAL_WHEEL)) {
      return -1;
    }

    Map<String, Integer> wheelToMove = new HashMap<>();
    wheelToMove.put(INITIAL_WHEEL, 0);
    Queue<String> queue = new ArrayDeque<>();
    queue.offer(INITIAL_WHEEL);
    while (!queue.isEmpty()) {
      String wheel = queue.poll();
      if (wheel.equals(target)) {
        return wheelToMove.get(wheel);
      }

      for (int i = 0; i < target.length(); ++i) {
        for (int offset : OFFSETS) {
          String nextWheel =
              String.format(
                  "%s%d%s",
                  wheel.substring(0, i),
                  (wheel.charAt(i) - '0' + offset + 10) % 10,
                  wheel.substring(i + 1));

          if (!deads.contains(nextWheel) && !wheelToMove.containsKey(nextWheel)) {
            wheelToMove.put(nextWheel, wheelToMove.get(wheel) + 1);
            queue.offer(nextWheel);
          }
        }
      }
    }

    return -1;
  }
}
