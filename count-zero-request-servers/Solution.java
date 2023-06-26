import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] countServers(int n, int[][] logs, int x, int[] queries) {
    Arrays.sort(logs, Comparator.comparing(log -> log[1]));

    int[] result = new int[queries.length];
    Map<Integer, Integer> serverToRequestCount = new HashMap<>();
    int leftLogIndex = 0;
    int rightLogIndex = -1;
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int queryIndex : sortedQueryIndices) {
      while (rightLogIndex != logs.length - 1
          && logs[rightLogIndex + 1][1] <= queries[queryIndex]) {
        updateMap(serverToRequestCount, logs[rightLogIndex + 1][0], 1);
        ++rightLogIndex;
      }

      while (leftLogIndex != logs.length && logs[leftLogIndex][1] < queries[queryIndex] - x) {
        updateMap(serverToRequestCount, logs[leftLogIndex][0], -1);
        ++leftLogIndex;
      }

      result[queryIndex] = n - serverToRequestCount.size();
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> serverToRequestCount, int server, int delta) {
    serverToRequestCount.put(server, serverToRequestCount.getOrDefault(server, 0) + delta);
    serverToRequestCount.remove(server, 0);
  }
}
