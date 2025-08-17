import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int xorAfterQueries(int[] nums, int[][] queries) {
    for (int[] query : queries) {
      for (int i = query[0]; i <= query[1]; i += query[2]) {
        nums[i] = multiplyMod(nums[i], query[3]);
      }
    }

    return Arrays.stream(nums).reduce((acc, x) -> acc ^ x).getAsInt();
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}