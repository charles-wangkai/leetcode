import java.util.stream.IntStream;

class Solution {
  public long maximumOr(int[] nums, int k) {
    int n = nums.length;

    int[] leftOrs = new int[n];
    for (int i = 0; i < leftOrs.length; ++i) {
      leftOrs[i] = ((i == 0) ? 0 : leftOrs[i - 1]) | nums[i];
    }

    int[] rightOrs = new int[n];
    for (int i = rightOrs.length - 1; i >= 0; --i) {
      rightOrs[i] = ((i == rightOrs.length - 1) ? 0 : rightOrs[i + 1]) | nums[i];
    }

    return IntStream.range(0, n)
        .mapToLong(
            i ->
                ((i == 0) ? 0 : leftOrs[i - 1])
                    | ((long) nums[i] << k)
                    | ((i == rightOrs.length - 1) ? 0 : rightOrs[i + 1]))
        .max()
        .getAsLong();
  }
}
