import java.util.Arrays;

class Solution {
  static final int LANE_NUM = 3;

  public int minSideJumps(int[] obstacles) {
    int[] minSideNums = {1, 0, 1};
    for (int obstacle : obstacles) {
      int[] nextMinSideNums = new int[LANE_NUM];
      for (int i = 0; i < nextMinSideNums.length; ++i) {
        nextMinSideNums[i] = (i == obstacle - 1) ? Integer.MAX_VALUE : minSideNums[i];
      }
      for (int i = 0; i < nextMinSideNums.length; ++i) {
        if (i != obstacle - 1) {
          nextMinSideNums[i] =
              Math.min(nextMinSideNums[i], Arrays.stream(nextMinSideNums).min().getAsInt() + 1);
        }
      }

      minSideNums = nextMinSideNums;
    }

    return Arrays.stream(minSideNums).min().getAsInt();
  }
}
