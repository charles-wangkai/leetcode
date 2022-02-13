import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    return IntStream.range(0, 1 << nums.length)
        .mapToObj(
            mask ->
                IntStream.range(0, nums.length)
                    .filter(i -> (mask & (1 << i)) != 0)
                    .map(i -> nums[i])
                    .boxed()
                    .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }
}
