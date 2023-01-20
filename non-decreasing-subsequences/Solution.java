import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> findSubsequences(int[] nums) {
    return List.copyOf(
        IntStream.range(0, 1 << nums.length)
            .mapToObj(
                mask ->
                    IntStream.range(0, nums.length).filter(i -> ((mask >> i) & 1) == 1).toArray())
            .filter(
                indices ->
                    indices.length >= 2
                        && IntStream.range(0, indices.length - 1)
                            .allMatch(i -> nums[indices[i]] <= nums[indices[i + 1]]))
            .map(indices -> Arrays.stream(indices).map(index -> nums[index]).boxed().toList())
            .collect(Collectors.toSet()));
  }
}
