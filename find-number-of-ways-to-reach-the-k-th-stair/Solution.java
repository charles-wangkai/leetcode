import static java.util.Map.entry;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int waysToReachStair(int k) {
    int leap = 1;
    Map<Integer, Integer> stairToCount = Map.ofEntries(entry(0, 1), entry(1, 1));
    int result = stairToCount.getOrDefault(k, 0);
    while (leap <= k + 1) {
      Map<Integer, Integer> nextStairToCount = new HashMap<>();
      for (int stair : stairToCount.keySet()) {
        if (stair + leap <= k) {
          updateMap(nextStairToCount, stair + leap, stairToCount.get(stair));
        }
        if (stair + leap - 1 <= k) {
          updateMap(nextStairToCount, stair + leap - 1, stairToCount.get(stair));
        }
      }

      stairToCount = nextStairToCount;
      result += stairToCount.getOrDefault(k, 0);

      leap <<= 1;
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}