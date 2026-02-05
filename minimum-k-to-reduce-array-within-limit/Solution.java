import java.util.Arrays;

class Solution {
  static final int LIMIT = 100000;

  public int minimumK(int[] nums) {
    int result = -1;
    int lower = 1;
    int upper = LIMIT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k) {
    return Arrays.stream(nums).map(x -> Math.ceilDiv(x, k)).asLongStream().sum() <= (long) k * k;
  }
}