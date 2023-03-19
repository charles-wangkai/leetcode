import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximizeGreatness(int[] nums) {
    Arrays.sort(nums);

    int result = -1;
    int lower = 0;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int greatness) {
    return IntStream.range(0, greatness).allMatch(i -> nums[i] < nums[nums.length - greatness + i]);
  }
}
