import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int longestConsecutive(int[] nums) {
    Map<Integer, Boolean> valueToAvailable = new HashMap<>();
    for (int num : nums) {
      valueToAvailable.put(num, true);
    }

    int maxLength = 0;
    for (int middle : List.copyOf(valueToAvailable.keySet())) {
      int length = 0;
      for (int i = middle; valueToAvailable.getOrDefault(i, false); ++i) {
        valueToAvailable.put(i, false);
        ++length;
      }
      for (int i = middle - 1; valueToAvailable.getOrDefault(i, false); --i) {
        valueToAvailable.put(i, false);
        ++length;
      }

      maxLength = Math.max(maxLength, length);
    }

    return maxLength;
  }
}
