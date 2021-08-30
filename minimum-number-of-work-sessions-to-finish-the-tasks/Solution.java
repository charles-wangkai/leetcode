import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minSessions(int[] tasks, int sessionTime) {
    return search(tasks, sessionTime, (1 << tasks.length) - 1, new HashMap<>());
  }

  int search(int[] tasks, int sessionTime, int restCode, Map<Integer, Integer> cache) {
    if (restCode == 0) {
      return 0;
    }

    if (!cache.containsKey(restCode)) {
      int[] indices =
          IntStream.range(0, tasks.length).filter(i -> (restCode & (1 << i)) != 0).toArray();

      int result = Integer.MAX_VALUE;
      for (int code = 0; code < 1 << (indices.length - 1); ++code) {
        int time = tasks[indices[indices.length - 1]];
        int nextRestCode = restCode - (1 << indices[indices.length - 1]);
        for (int i = 0; i < indices.length - 1; ++i) {
          if ((code & (1 << i)) != 0) {
            time += tasks[indices[i]];
            nextRestCode -= 1 << indices[i];
          }
        }

        if (time <= sessionTime) {
          result = Math.min(result, 1 + search(tasks, sessionTime, nextRestCode, cache));
        }
      }

      cache.put(restCode, result);
    }

    return cache.get(restCode);
  }
}
