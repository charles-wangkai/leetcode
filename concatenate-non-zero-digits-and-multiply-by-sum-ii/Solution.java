import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] sumAndMultiply(String s, int[][] queries) {
    int[] prefixDigitSum = new int[s.length() + 1];
    int[] prefixNonZeroCounts = new int[s.length() + 1];
    int[] prefixCompressed = new int[s.length() + 1];
    for (int i = 1; i <= s.length(); ++i) {
      int digit = s.charAt(i - 1) - '0';

      prefixDigitSum[i] = prefixDigitSum[i - 1] + digit;
      prefixNonZeroCounts[i] = prefixNonZeroCounts[i - 1] + ((digit == 0) ? 0 : 1);
      prefixCompressed[i] =
          (digit == 0)
              ? prefixCompressed[i - 1]
              : addMod(multiplyMod(prefixCompressed[i - 1], 10), digit);
    }

    int[] tenPowers = new int[s.length() + 1];
    tenPowers[0] = 1;
    for (int i = 1; i < tenPowers.length; ++i) {
      tenPowers[i] = multiplyMod(tenPowers[i - 1], 10);
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                multiplyMod(
                    addMod(
                        prefixCompressed[query[1] + 1],
                        -multiplyMod(
                            prefixCompressed[query[0]],
                            tenPowers[
                                prefixNonZeroCounts[query[1] + 1]
                                    - prefixNonZeroCounts[query[0]]])),
                    prefixDigitSum[query[1] + 1] - prefixDigitSum[query[0]]))
        .toArray();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}