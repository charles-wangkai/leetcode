import java.util.stream.IntStream;

class Solution {
  public int sortableIntegers(int[] nums) {
    return IntStream.rangeClosed(1, nums.length).filter(k -> isSortable(nums, k)).sum();
  }

  boolean isSortable(int[] nums, int k) {
    if (nums.length % k != 0) {
      return false;
    }

    int prev = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length / k; ++i) {
      int beginIndex = i * k;
      if (IntStream.range(0, k)
                  .filter(j -> nums[beginIndex + j] > nums[beginIndex + (j + 1) % k])
                  .count()
              > 1
          || IntStream.range(0, k).map(j -> nums[beginIndex + j]).min().getAsInt() < prev) {
        return false;
      }

      prev = IntStream.range(0, k).map(j -> nums[beginIndex + j]).max().getAsInt();
    }

    return true;
  }
}