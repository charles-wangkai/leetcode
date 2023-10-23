import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minGroupsForValidAssignment(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int[] counts = valueToCount.values().stream().sorted().mapToInt(Integer::intValue).toArray();
    for (int size = counts[0]; ; --size) {
      int size_ = size;
      if (Arrays.stream(counts).allMatch(count -> count % size_ <= count / size_)) {
        return Arrays.stream(counts).map(count -> (count + size_) / (size_ + 1)).sum();
      }
    }
  }
}
