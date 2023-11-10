import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public int[] restoreArray(int[][] adjacentPairs) {
    Map<Integer, List<Integer>> valueToAdjs = new HashMap<>();
    for (int[] adjacentPair : adjacentPairs) {
      valueToAdjs.putIfAbsent(adjacentPair[0], new ArrayList<>());
      valueToAdjs.get(adjacentPair[0]).add(adjacentPair[1]);

      valueToAdjs.putIfAbsent(adjacentPair[1], new ArrayList<>());
      valueToAdjs.get(adjacentPair[1]).add(adjacentPair[0]);
    }

    int[] result = new int[adjacentPairs.length + 1];
    result[0] =
        valueToAdjs.keySet().stream()
            .filter(value -> valueToAdjs.get(value).size() == 1)
            .findAny()
            .get();
    Set<Integer> seen = new HashSet<>();
    seen.add(result[0]);
    for (int i = 1; i < result.length; ++i) {
      result[i] =
          valueToAdjs.get(result[i - 1]).stream()
              .filter(adj -> !seen.contains(adj))
              .findAny()
              .get();
      seen.add(result[i]);
    }

    return result;
  }
}
