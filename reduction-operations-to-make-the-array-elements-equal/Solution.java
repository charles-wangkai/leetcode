import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int reductionOperations(int[] nums) {
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>(Comparator.reverseOrder());
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int result = 0;
    int sum = 0;
    for (int value : valueToCount.keySet()) {
      if (value != valueToCount.lastKey()) {
        sum += valueToCount.get(value);
        result += sum;
      }
    }

    return result;
  }
}
