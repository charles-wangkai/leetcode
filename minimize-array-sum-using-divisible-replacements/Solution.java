import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public long minArraySum(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int[] sortedValues =
        valueToCount.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();

    long result = 0;
    Set<Integer> seen = new HashSet<>();
    for (int value : sortedValues) {
      if (!seen.contains(value)) {
        for (int multiple = value;
            multiple <= sortedValues[sortedValues.length - 1];
            multiple += value) {
          if (!seen.contains(multiple) && valueToCount.containsKey(multiple)) {
            result += (long) valueToCount.get(multiple) * value;
            seen.add(multiple);
          }
        }
      }
    }

    return result;
  }
}