import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findValidElements(int[] nums) {
    return IntStream.range(0, nums.length)
        .filter(
            i ->
                nums[i] > IntStream.range(0, i).map(j -> nums[j]).max().orElse(Integer.MIN_VALUE)
                    || nums[i]
                        > IntStream.range(i + 1, nums.length)
                            .map(j -> nums[j])
                            .max()
                            .orElse(Integer.MIN_VALUE))
        .map(i -> nums[i])
        .boxed()
        .toList();
  }
}