import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumWidth(int[] planks) {
    Map<Integer, Integer> plankToCount = new HashMap<>();
    for (int plank : planks) {
      plankToCount.put(plank, plankToCount.getOrDefault(plank, 0) + 1);
    }

    Map<Integer, Integer> heightToCount = new HashMap<>();
    for (int plank : plankToCount.keySet()) {
      heightToCount.put(plank, heightToCount.getOrDefault(plank, 0) + plankToCount.get(plank));
      heightToCount.put(
          plank * 2, heightToCount.getOrDefault(plank * 2, 0) + plankToCount.get(plank) / 2);
    }
    for (int plank1 : plankToCount.keySet()) {
      for (int plank2 : plankToCount.keySet()) {
        if (plank1 < plank2) {
          int height = plank1 + plank2;
          heightToCount.put(
              height,
              heightToCount.getOrDefault(height, 0)
                  + Math.min(plankToCount.get(plank1), plankToCount.get(plank2)));
        }
      }
    }

    return heightToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}