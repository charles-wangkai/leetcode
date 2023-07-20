import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
    return IntStream.range(0, nums.length)
        .filter(
            i ->
                IntStream.rangeClosed(i - k, i + k)
                    .anyMatch(j -> j >= 0 && j < nums.length && nums[j] == key))
        .boxed()
        .toList();
  }
}
