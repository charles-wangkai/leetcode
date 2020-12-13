import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int DIRECTION_NUM = 6;

  public int maxHeight(int[][] cuboids) {
    int n = cuboids.length;

    Arrays.sort(
        cuboids,
        Comparator.<int[]>comparingInt(cuboid -> cuboid[0] * cuboid[1] * cuboid[2]).reversed());

    int[][] dp = new int[n][DIRECTION_NUM];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < DIRECTION_NUM; ++j) {
        int[] sizes = buildSizes(cuboids[i], j);
        dp[i][j] = sizes[2];

        for (int prevI = 0; prevI < i; ++prevI) {
          for (int prevJ = 0; prevJ < DIRECTION_NUM; ++prevJ) {
            int[] prevSizes = buildSizes(cuboids[prevI], prevJ);

            if (canStack(sizes, prevSizes)) {
              dp[i][j] = Math.max(dp[i][j], dp[prevI][prevJ] + sizes[2]);
            }
          }
        }
      }
    }

    int result = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < DIRECTION_NUM; ++j) {
        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }

  static boolean canStack(int[] sizes, int[] prevSizes) {
    return IntStream.range(0, sizes.length).allMatch(i -> sizes[i] <= prevSizes[i]);
  }

  static int[] buildSizes(int[] cuboid, int seq) {
    if (seq == 0) {
      return new int[] {cuboid[0], cuboid[1], cuboid[2]};
    } else if (seq == 1) {
      return new int[] {cuboid[0], cuboid[2], cuboid[1]};
    } else if (seq == 2) {
      return new int[] {cuboid[1], cuboid[0], cuboid[2]};
    } else if (seq == 3) {
      return new int[] {cuboid[1], cuboid[2], cuboid[0]};
    } else if (seq == 4) {
      return new int[] {cuboid[2], cuboid[0], cuboid[1]};
    } else {
      return new int[] {cuboid[2], cuboid[1], cuboid[0]};
    }
  }
}
