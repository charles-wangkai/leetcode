import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int k) {
    int result = Integer.MAX_VALUE;
    for (int x = 0; x < k; ++x) {
      for (int y = 0; y < k; ++y) {
        if (y != x) {
          int x_ = x;
          int y_ = y;
          result =
              Math.min(
                  result,
                  IntStream.range(0, nums.length)
                      .map(i -> computeOperationNum(k, nums[i], (i % 2 == 0) ? x_ : y_))
                      .sum());
        }
      }
    }

    return result;
  }

  int computeOperationNum(int k, int value, int target) {
    int remainder = value % k;

    return Math.min(Math.abs(remainder - target), k - Math.abs(remainder - target));
  }
}