import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public int[] findingUsersActiveMinutes(int[][] logs, int k) {
    Map<Integer, Set<Integer>> idToTimes = new HashMap<>();
    for (int[] log : logs) {
      if (!idToTimes.containsKey(log[0])) {
        idToTimes.put(log[0], new HashSet<>());
      }
      idToTimes.get(log[0]).add(log[1]);
    }

    int[] result = new int[k];
    for (Set<Integer> times : idToTimes.values()) {
      ++result[times.size() - 1];
    }

    return result;
  }
}
