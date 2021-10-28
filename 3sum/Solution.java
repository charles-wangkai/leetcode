import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      updateMap(valueToCount, value, 1);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (int a : List.copyOf(valueToCount.keySet())) {
      updateMap(valueToCount, a, -1);

      for (int b : List.copyOf(valueToCount.keySet())) {
        if (b >= a) {
          updateMap(valueToCount, b, -1);

          int c = -a - b;
          if (c >= b && valueToCount.containsKey(c)) {
            result.add(List.of(a, b, c));
          }

          updateMap(valueToCount, b, 1);
        }
      }

      updateMap(valueToCount, a, 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
