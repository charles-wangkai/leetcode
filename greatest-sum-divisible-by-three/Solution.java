import java.util.Arrays;

class Solution {
  public int maxSumDivThree(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    int[] remainders1 = Arrays.stream(nums).filter(x -> x % 3 == 1).sorted().toArray();
    int[] remainders2 = Arrays.stream(nums).filter(x -> x % 3 == 2).sorted().toArray();

    if (sum % 3 == 0) {
      return sum;
    }
    if (sum % 3 == 1) {
      return Math.max(
          (remainders1.length >= 1) ? (sum - remainders1[0]) : Integer.MIN_VALUE,
          (remainders2.length >= 2) ? (sum - remainders2[0] - remainders2[1]) : Integer.MIN_VALUE);
    }

    return Math.max(
        (remainders1.length >= 2) ? (sum - remainders1[0] - remainders1[1]) : Integer.MIN_VALUE,
        (remainders2.length >= 1) ? (sum - remainders2[0]) : Integer.MIN_VALUE);
  }
}
