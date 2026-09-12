import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
                    indices.size() >= 3
                        && IntStream.range(0, indices.size() - 1)
                                .map(i -> indices.get(i + 1) - indices.get(i))
                                .distinct()
                                .count()
                            == 1)
            .count();
  }
}