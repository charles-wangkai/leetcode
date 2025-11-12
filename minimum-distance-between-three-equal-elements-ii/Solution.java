import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumDistance(int[] nums) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    return valueToIndices.values().stream()
        .flatMapToInt(
            indices ->
                IntStream.range(1, indices.size() - 1)
                    .map(i -> (indices.get(i + 1) - indices.get(i - 1)) * 2))
        .min()
        .orElse(-1);
  }
}