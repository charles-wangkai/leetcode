import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int countSpecialIntegers(int[] nums) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    return (int)
        valueToIndices.values().stream()
            .filter(
                indices ->
                    indices.size() == 3
                        && indices.get(1) - indices.get(0) == indices.get(2) - indices.get(1))
            .count();
  }
}