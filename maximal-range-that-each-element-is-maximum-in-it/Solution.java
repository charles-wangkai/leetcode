import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public int[] maximumLengthOfRanges(int[] nums) {
    SortedMap<Integer, Integer> valueToIndices = new TreeMap<>(Comparator.reverseOrder());
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.put(nums[i], i);
    }

    NavigableSet<Integer> inserted = new TreeSet<>();
    inserted.add(-1);
    inserted.add(nums.length);

    int[] result = new int[nums.length];
    for (int value : valueToIndices.keySet()) {
      int index = valueToIndices.get(value);
      result[index] = inserted.higher(index) - inserted.lower(index) - 1;

      inserted.add(index);
    }

    return result;
  }
}
