import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] maxSubsequence(int[] nums, int k) {
    Set<Integer> sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> nums[i]).reversed())
            .limit(k)
            .collect(Collectors.toSet());

    return IntStream.range(0, nums.length)
        .filter(sortedIndices::contains)
        .map(i -> nums[i])
        .toArray();
  }
}