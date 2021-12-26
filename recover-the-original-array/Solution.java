import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] recoverArray(int[] nums) {
    Arrays.sort(nums);

    for (int i = 1; ; ++i) {
      if (nums[i] != nums[0] && (nums[i] - nums[0]) % 2 == 0) {
        int[] original = recover(nums, (nums[i] - nums[0]) / 2);
        if (original != null) {
          return original;
        }
      }
    }
  }

  int[] recover(int[] nums, int k) {
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int value : nums) {
      updateMap(valueToCount, value, 1);
    }

    List<Integer> result = new ArrayList<>();
    while (!valueToCount.isEmpty()) {
      int higher = valueToCount.lastKey();
      updateMap(valueToCount, higher, -1);

      int lower = higher - 2 * k;
      if (!valueToCount.containsKey(lower)) {
        return null;
      }
      updateMap(valueToCount, lower, -1);

      result.add(higher - k);
    }

    return result.stream().mapToInt(x -> x).toArray();
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}