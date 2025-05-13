import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public int minOperations(int[] nums) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int result = 0;
    NavigableSet<Integer> seen = new TreeSet<>();
    for (int value : valueToIndices.keySet()) {
      int prev = -1;
      for (int index : valueToIndices.get(value)) {
        seen.add(index);

        if (value != 0) {
          Integer lower = seen.lower(index);
          if (lower == null || lower != prev) {
            ++result;
          }
        }

        prev = index;
      }
    }

    return result;
  }
}