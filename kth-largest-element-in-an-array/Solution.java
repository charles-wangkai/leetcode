import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findKthLargest(int[] nums, int k) {
    return Arrays.stream(nums)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .skip(k - 1)
        .findFirst()
        .get();
  }
}
