import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);

    int result = -1;
    int lower =
        IntStream.range(0, nums.length - 1).map(i -> nums[i + 1] - nums[i]).min().getAsInt();
    int upper = nums[nums.length - 1] - nums[0];
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int distance) {
    int pairNum = 0;
    int endIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (endIndex != nums.length - 1 && nums[endIndex + 1] - nums[i] <= distance) {
        ++endIndex;
      }
      pairNum += endIndex - i;
    }

    return pairNum >= k;
  }
}
