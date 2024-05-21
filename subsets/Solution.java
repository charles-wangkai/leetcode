import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    return IntStream.range(0, 1 << nums.length)
        .mapToObj(
            mask ->
                IntStream.range(0, nums.length)
                    .filter(i -> ((mask >> i) & 1) == 1)
                    .map(i -> nums[i])
                    .boxed()
                    .toList())
        .toList();
  }
}
