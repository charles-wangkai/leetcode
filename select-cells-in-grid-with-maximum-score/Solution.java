import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public int maxScore(List<List<Integer>> grid) {
    Map<Integer, Set<Integer>> valueToRows = new HashMap<>();
    for (int r = 0; r < grid.size(); ++r) {
      for (int value : grid.get(r)) {
        valueToRows.putIfAbsent(value, new HashSet<>());
        valueToRows.get(value).add(r);
      }
    }

    Map<Integer, Integer> maskToScore = Map.of(0, 0);
    for (int value : valueToRows.keySet()) {
      Map<Integer, Integer> nextMaskToScore = new HashMap<>(maskToScore);
      for (int mask : maskToScore.keySet()) {
        for (int r : valueToRows.get(value)) {
          if (((mask >> r) & 1) == 0) {
            int nextMask = mask + (1 << r);
            nextMaskToScore.put(
                nextMask,
                Math.max(nextMaskToScore.getOrDefault(nextMask, 0), maskToScore.get(mask) + value));
          }
        }
      }

      maskToScore = nextMaskToScore;
    }

    return maskToScore.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}