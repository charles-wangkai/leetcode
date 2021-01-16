import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int findKthLargest(int[] nums, int k) {
    return Arrays.stream(nums)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(x -> x)
        .skip(k - 1)
        .findFirst()
        .getAsInt();
  }
}
