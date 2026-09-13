import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public long shadowPairs(int[] nums) {
    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    long result = 0;
    NavigableSet<Integer> seen = new TreeSet<>();
    for (List<Integer> indices : valueToIndices.values()) {
      int endIndex = 0;
      for (int i = 0; i < indices.size(); ++i) {
        Integer higher = seen.higher(indices.get(i));
        int rightIndex = (higher == null) ? nums.length : higher;

        while (endIndex != indices.size() - 1 && indices.get(endIndex + 1) < rightIndex) {
          ++endIndex;
        }

        result += (rightIndex - indices.get(i)) - (endIndex - i + 1);
      }

      seen.addAll(indices);
    }

    return result;
  }
}