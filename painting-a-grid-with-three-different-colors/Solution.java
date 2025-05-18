import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int COLOR_NUM = 3;

  public int colorTheGrid(int m, int n) {
    int[] dp = new int[pow(COLOR_NUM, m)];
    List<Integer> validCodes = new ArrayList<>();
    for (int i = 0; i < dp.length; ++i) {
      int[] colors = decode(m, i);
      if (isValid(colors)) {
        dp[i] = 1;
        validCodes.add(i);
      }
    }

    for (int i = 0; i < n - 1; ++i) {
      int[] nextDp = new int[dp.length];
      for (int j : validCodes) {
        int[] colors = decode(m, j);

        for (int k : validCodes) {
          int[] prevColors = decode(m, k);

          if (check(colors, prevColors)) {
            nextDp[j] = addMod(nextDp[j], dp[k]);
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).reduce(this::addMod).getAsInt();
  }

  int pow(int base, int exponent) {
    return IntStream.range(0, exponent).reduce(1, (x, i) -> x * base);
  }

  int[] decode(int size, int code) {
    int[] result = new int[size];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = code % COLOR_NUM;
      code /= COLOR_NUM;
    }

    return result;
  }

  boolean isValid(int[] colors) {
    return IntStream.range(0, colors.length - 1).allMatch(i -> colors[i] != colors[i + 1]);
  }

  boolean check(int[] colors1, int[] colors2) {
    for (int i = 0; i < colors1.length; ++i) {
      if (colors1[i] == colors2[i]) {
        return false;
      }
    }

    return true;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
