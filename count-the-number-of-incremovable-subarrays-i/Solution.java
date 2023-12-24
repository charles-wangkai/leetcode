import java.util.stream.IntStream;

class Solution {
  public int incremovableSubarrayCount(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i; j < nums.length; ++j) {
        int i_ = i;
        int j_ = j;
        if (isIncreasing(
            IntStream.range(0, nums.length)
                .filter(p -> p < i_ || p > j_)
                .map(p -> nums[p])
                .toArray())) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isIncreasing(int[] a) {
    return IntStream.range(0, a.length - 1).allMatch(i -> a[i] < a[i + 1]);
  }
}