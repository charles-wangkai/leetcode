import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<List<Integer>> findMatrix(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (int value : valueToCount.keySet()) {
      for (int i = 0; i < valueToCount.get(value); ++i) {
        if (i == result.size()) {
          result.add(new ArrayList<>());
        }
        result.get(i).add(value);
      }
    }

    return result;
  }
}
