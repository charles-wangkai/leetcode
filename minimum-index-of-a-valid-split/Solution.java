import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minimumIndex(List<Integer> nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int dominantValue =
        valueToCount.keySet().stream().max(Comparator.comparing(valueToCount::get)).get();

    int leftDominantCount = 0;
    for (int i = 0; i < nums.size() - 1; ++i) {
      if (nums.get(i) == dominantValue) {
        ++leftDominantCount;
      }

      if (leftDominantCount * 2 > i + 1
          && (valueToCount.get(dominantValue) - leftDominantCount) * 2 > nums.size() - i - 1) {
        return i;
      }
    }

    return -1;
  }
}
