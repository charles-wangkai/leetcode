import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxNumOfMarkedIndices(int[] nums) {
    Arrays.sort(nums);

    int result = 0;
    int lower = 0;
    int upper = nums.length / 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, middle)) {
        result = middle * 2;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int pairNum) {
    return IntStream.range(0, pairNum)
        .allMatch(i -> nums[i] * 2 <= nums[nums.length - pairNum + i]);
  }
}
