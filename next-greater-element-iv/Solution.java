import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public int[] secondGreaterElement(int[] nums) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>(Comparator.reverseOrder());
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int[] result = new int[nums.length];
    Arrays.fill(result, -1);
    NavigableSet<Integer> greaterIndices = new TreeSet<>();
    for (List<Integer> indices : valueToIndices.values()) {
      for (int index : indices) {
        Integer firstGreaterIndex = greaterIndices.higher(index);
        if (firstGreaterIndex != null) {
          Integer secondGreaterIndex = greaterIndices.higher(firstGreaterIndex);
          if (secondGreaterIndex != null) {
            result[index] = nums[secondGreaterIndex];
          }
        }
      }

      greaterIndices.addAll(indices);
    }

    return result;
  }
}
