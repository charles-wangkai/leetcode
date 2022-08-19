import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isPossible(int[] nums) {
    Map<Integer, Integer> valueToNeeded = new HashMap<>();
    for (int value : nums) {
      valueToNeeded.put(value, valueToNeeded.getOrDefault(value, 0) + 1);
    }

    Map<Integer, Integer> valueToAppendable = new HashMap<>();
    for (int value : nums) {
      if (valueToNeeded.get(value) != 0) {
        if (valueToAppendable.getOrDefault(value, 0) != 0) {
          valueToAppendable.put(value, valueToAppendable.get(value) - 1);
          valueToAppendable.put(value + 1, valueToAppendable.getOrDefault(value + 1, 0) + 1);
        } else if (valueToNeeded.getOrDefault(value + 1, 0) != 0
            && valueToNeeded.getOrDefault(value + 2, 0) != 0) {
          valueToNeeded.put(value + 1, valueToNeeded.get(value + 1) - 1);
          valueToNeeded.put(value + 2, valueToNeeded.get(value + 2) - 1);
          valueToAppendable.put(value + 3, valueToAppendable.getOrDefault(value + 3, 0) + 1);
        } else {
          return false;
        }

        valueToNeeded.put(value, valueToNeeded.get(value) - 1);
      }
    }

    return true;
  }
}
