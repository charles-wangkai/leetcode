import java.util.stream.IntStream;

class Solution {
  public int minFlips(String s) {
    int n = s.length();

    int[][][] leftCounts = new int[2][n + 2][2];
    for (int i = 0; i < n; ++i) {
      for (int parity = 0; parity < 2; ++parity) {
        for (int bit = 0; bit < 2; ++bit) {
          leftCounts[parity][i + 1][bit] = leftCounts[parity][i][bit];
        }
      }

      ++leftCounts[i % 2][i + 1][s.charAt(i) - '0'];
    }

    if (n % 2 == 0) {
      return Math.min(
          leftCounts[0][n][1] + leftCounts[1][n][0], leftCounts[0][n][0] + leftCounts[1][n][1]);
    }

    int[][][] rightCounts = new int[2][n + 2][2];
    for (int i = n - 1; i >= 0; --i) {
      for (int parity = 0; parity < 2; ++parity) {
        for (int bit = 0; bit < 2; ++bit) {
          rightCounts[parity][i + 1][bit] = rightCounts[parity][i + 2][bit];
        }
      }

      ++rightCounts[i % 2][i + 1][s.charAt(i) - '0'];
    }

    return IntStream.range(0, n)
        .flatMap(
            i ->
                IntStream.range(0, 2)
                    .flatMap(
                        bit ->
                            IntStream.of(
                                ((s.charAt(i) == bit + '0') ? 0 : 1)
                                    + rightCounts[i % 2][i + 2][1 - bit]
                                    + rightCounts[1 - i % 2][i + 2][bit]
                                    + leftCounts[i % 2][i][bit]
                                    + leftCounts[1 - i % 2][i][1 - bit])))
        .min()
        .getAsInt();
  }
}
