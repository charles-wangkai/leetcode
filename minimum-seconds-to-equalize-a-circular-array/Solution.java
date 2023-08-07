import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumSeconds(List<Integer> nums) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.size(); ++i) {
      valueToIndices.putIfAbsent(nums.get(i), new ArrayList<>());
      valueToIndices.get(nums.get(i)).add(i);
    }

    return valueToIndices.values().stream()
        .mapToInt(
            indices ->
                IntStream.range(0, indices.size())
                    .map(
                        i ->
                            (((i == indices.size() - 1)
                                        ? (indices.get(0) + nums.size())
                                        : indices.get(i + 1))
                                    - indices.get(i))
                                / 2)
                    .max()
                    .getAsInt())
        .min()
        .getAsInt();
  }
}
