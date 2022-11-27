import java.util.stream.IntStream;

class Solution {
  static final int DIGIT_NUM = 10;
  static final int MODULUS = 1_000_000_007;

  public int countPalindromes(String s) {
    int[][] leftCounts = new int[s.length()][DIGIT_NUM];
    for (int i = 0; i < leftCounts.length; ++i) {
      for (int d = 0; d < DIGIT_NUM; ++d) {
        leftCounts[i][d] =
            ((i == 0) ? 0 : leftCounts[i - 1][d]) + ((s.charAt(i) - '0' == d) ? 1 : 0);
      }
    }

    int[][][] leftCombCounts = new int[s.length()][DIGIT_NUM][DIGIT_NUM];
    for (int i = 1; i < leftCombCounts.length; ++i) {
      for (int d1 = 0; d1 < DIGIT_NUM; ++d1) {
        for (int d2 = 0; d2 < DIGIT_NUM; ++d2) {
          leftCombCounts[i][d1][d2] = leftCombCounts[i - 1][d1][d2];
        }
      }

      for (int d2 = 0; d2 < DIGIT_NUM; ++d2) {
        leftCombCounts[i][s.charAt(i) - '0'][d2] += leftCounts[i - 1][d2];
      }
    }

    int[][] rightCounts = new int[s.length()][DIGIT_NUM];
    for (int i = rightCounts.length - 1; i >= 0; --i) {
      for (int d = 0; d < DIGIT_NUM; ++d) {
        rightCounts[i][d] =
            ((i == rightCounts.length - 1) ? 0 : rightCounts[i + 1][d])
                + ((s.charAt(i) - '0' == d) ? 1 : 0);
      }
    }

    int[][][] rightCombCounts = new int[s.length()][DIGIT_NUM][DIGIT_NUM];
    for (int i = rightCombCounts.length - 2; i >= 0; --i) {
      for (int d1 = 0; d1 < DIGIT_NUM; ++d1) {
        for (int d2 = 0; d2 < DIGIT_NUM; ++d2) {
          rightCombCounts[i][d1][d2] = rightCombCounts[i + 1][d1][d2];
        }
      }

      for (int d2 = 0; d2 < DIGIT_NUM; ++d2) {
        rightCombCounts[i][s.charAt(i) - '0'][d2] += rightCounts[i + 1][d2];
      }
    }

    return IntStream.rangeClosed(2, s.length() - 3)
        .map(
            i -> {
              int result = 0;
              for (int d1 = 0; d1 < DIGIT_NUM; ++d1) {
                for (int d2 = 0; d2 < DIGIT_NUM; ++d2) {
                  result =
                      addMod(
                          result,
                          multiplyMod(
                              leftCombCounts[i - 1][d1][d2], rightCombCounts[i + 1][d1][d2]));
                }
              }

              return result;
            })
        .reduce(0, this::addMod);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
