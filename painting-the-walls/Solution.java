import java.util.HashMap;
import java.util.Map;

class Solution {
  public int paintWalls(int[] cost, int[] time) {
    Map<Integer, Integer> timeDiffToMinCost = Map.of(0, 0);
    for (int i = 0; i < cost.length; ++i) {
      Map<Integer, Integer> nextTimeDiffToMinCost = new HashMap<>();
      for (int timeDiff : timeDiffToMinCost.keySet()) {
        nextTimeDiffToMinCost.put(
            timeDiff - 1,
            Math.min(
                nextTimeDiffToMinCost.getOrDefault(timeDiff - 1, Integer.MAX_VALUE),
                timeDiffToMinCost.get(timeDiff)));

        nextTimeDiffToMinCost.put(
            timeDiff + time[i],
            Math.min(
                nextTimeDiffToMinCost.getOrDefault(timeDiff + time[i], Integer.MAX_VALUE),
                timeDiffToMinCost.get(timeDiff) + cost[i]));
      }

      timeDiffToMinCost = nextTimeDiffToMinCost;
    }

    return timeDiffToMinCost.keySet().stream()
        .filter(timeDiff -> timeDiff >= 0)
        .mapToInt(timeDiffToMinCost::get)
        .min()
        .getAsInt();
  }
}
