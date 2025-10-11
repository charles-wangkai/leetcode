import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minUnlockedIndices(int[] nums, int[] locked) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    if (valueToIndices.containsKey(1)
        && valueToIndices.containsKey(3)
        && valueToIndices.get(1).getLast() > valueToIndices.get(3).getFirst()) {
      return -1;
    }

    return ((valueToIndices.containsKey(1) && valueToIndices.containsKey(2))
            ? (int)
                IntStream.range(valueToIndices.get(2).getFirst(), valueToIndices.get(1).getLast())
                    .filter(i -> locked[i] == 1)
                    .count()
            : 0)
        + ((valueToIndices.containsKey(2) && valueToIndices.containsKey(3))
            ? (int)
                IntStream.range(valueToIndices.get(3).getFirst(), valueToIndices.get(2).getLast())
                    .filter(i -> locked[i] == 1)
                    .count()
            : 0);
  }
}