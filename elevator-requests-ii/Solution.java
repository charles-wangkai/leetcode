import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long elevatorRequests(int n, int start, int[] requests) {
    int[] floors =
        IntStream.concat(IntStream.of(start), Arrays.stream(requests))
            .sorted()
            .distinct()
            .toArray();
    int floorNum = floors.length;

    long[][][] dp = new long[floorNum][floorNum][2];
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        Arrays.fill(dp[i][j], Long.MAX_VALUE);
      }
    }

    for (int length = 1; length <= floorNum; ++length) {
      for (int beginIndex = 0; beginIndex + length <= floorNum; ++beginIndex) {
        if (length == 1) {
          if (floors[beginIndex] == start) {
            dp[beginIndex][beginIndex][0] = 0;
            dp[beginIndex][beginIndex][1] = 0;
          }
        } else {
          int endIndex = beginIndex + length - 1;
          int factor = floorNum - length + 1;

          updateDp(dp, floors, factor, beginIndex, endIndex, 0, beginIndex + 1, endIndex, 0);
          updateDp(dp, floors, factor, beginIndex, endIndex, 0, beginIndex + 1, endIndex, 1);
          updateDp(dp, floors, factor, beginIndex, endIndex, 1, beginIndex, endIndex - 1, 0);
          updateDp(dp, floors, factor, beginIndex, endIndex, 1, beginIndex, endIndex - 1, 1);
        }
      }
    }

    return Math.min(dp[0][floorNum - 1][0], dp[0][floorNum - 1][1]);
  }

  void updateDp(
      long[][][] dp,
      int[] floors,
      int factor,
      int beginIndex,
      int endIndex,
      int side,
      int prevBeginIndex,
      int prevEndIndex,
      int prevSide) {
    if (dp[prevBeginIndex][prevEndIndex][prevSide] != Long.MAX_VALUE) {
      dp[beginIndex][endIndex][side] =
          Math.min(
              dp[beginIndex][endIndex][side],
              dp[prevBeginIndex][prevEndIndex][prevSide]
                  + (long)
                          Math.abs(
                              floors[(side == 0) ? beginIndex : endIndex]
                                  - floors[(prevSide == 0) ? prevBeginIndex : prevEndIndex])
                      * factor);
    }
  }
}