import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> targetIndices(int[] nums, int target) {
    int[] sorted = Arrays.stream(nums).sorted().toArray();

    return IntStream.range(0, nums.length)
        .filter(i -> sorted[i] == target)
        .boxed()
        .collect(Collectors.toList());
  }
}