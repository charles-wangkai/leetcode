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
      addValueToAdjs(valueToAdjs, adjacentPair[0], adjacentPair[1]);
      addValueToAdjs(valueToAdjs, adjacentPair[1], adjacentPair[0]);
    }

    int[] result = new int[adjacentPairs.length + 1];
    result[0] =
        valueToAdjs.keySet().stream()
            .filter(value -> valueToAdjs.get(value).size() == 1)
            .findAny()
            .get();
    Set<Integer> used = new HashSet<>();
    used.add(result[0]);
    for (int i = 1; i < result.length; ++i) {
      result[i] =
          valueToAdjs.get(result[i - 1]).stream()
              .filter(adj -> !used.contains(adj))
              .findAny()
              .get();
      used.add(result[i]);
    }

    return result;
  }

  void addValueToAdjs(Map<Integer, List<Integer>> valueToAdjs, int value, int adj) {
    if (!valueToAdjs.containsKey(value)) {
      valueToAdjs.put(value, new ArrayList<>());
    }
    valueToAdjs.get(value).add(adj);
  }
}
