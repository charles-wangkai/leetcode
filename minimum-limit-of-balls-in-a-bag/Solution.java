import java.util.Arrays;

class Solution {
  public int minimumSize(int[] nums, int maxOperations) {
    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(nums).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, maxOperations, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int maxOperations, int maxBall) {
    return Arrays.stream(nums).map(num -> (num + maxBall - 1) / maxBall).asLongStream().sum()
        <= nums.length + maxOperations;
  }
}
