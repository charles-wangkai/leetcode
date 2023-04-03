import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<List<Integer>> findMatrix(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      updateMap(valueToCount, num, 1);
    }

    List<List<Integer>> result = new ArrayList<>();
    while (!valueToCount.isEmpty()) {
      List<Integer> row = List.copyOf(valueToCount.keySet());
      for (int value : row) {
        updateMap(valueToCount, value, -1);
      }

      result.add(row);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
