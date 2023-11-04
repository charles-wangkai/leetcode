import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int x, int y) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(nums).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, x, y, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int x, int y, int operationNum) {
    return Arrays.stream(nums)
            .mapToLong(num -> (Math.max(0, num - (long) y * operationNum) + (x - y - 1)) / (x - y))
            .sum()
        <= operationNum;
  }
}
