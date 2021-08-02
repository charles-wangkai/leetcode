import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countSpecialSubsequences(int[] nums) {
    int[] wayNums = new int[3];
    for (int num : nums) {
      int[] nextWayNums = Arrays.copyOf(wayNums, wayNums.length);

      if (num == 0) {
        nextWayNums[0] = addMod(nextWayNums[0], addMod(1, wayNums[0]));
      } else if (num == 1) {
        nextWayNums[1] = addMod(nextWayNums[1], addMod(wayNums[0], wayNums[1]));
      } else {
        nextWayNums[2] = addMod(nextWayNums[2], addMod(wayNums[1], wayNums[2]));
      }

      wayNums = nextWayNums;
    }

    return wayNums[2];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
