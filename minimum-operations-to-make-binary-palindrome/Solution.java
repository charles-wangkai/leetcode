import java.util.Arrays;

class Solution {
  public int[] minOperations(int[] nums) {
    return Arrays.stream(nums)
        .map(x -> Math.min(computeOperationNum(x, 1), computeOperationNum(x, -1)))
        .toArray();
  }

  int computeOperationNum(int value, int offset) {
    for (int i = 0; ; ++i) {
      String s = String.valueOf(Integer.toBinaryString(value + offset * i));
      if (new StringBuilder(s).reverse().toString().equals(s)) {
        return i;
      }
    }
  }
}