import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numWays(String[] words, String target) {
    int[][] counts = new int[words[0].length()][26];
    for (int i = 0; i < counts.length; ++i) {
      for (String word : words) {
        ++counts[i][word.charAt(i) - 'a'];
      }
    }

    int[] wayNums = new int[target.length() + 1];
    wayNums[0] = 1;
    for (int i = 0; i < counts.length; ++i) {
      int[] nextWayNums = Arrays.copyOf(wayNums, wayNums.length);
      for (int j = 1; j <= target.length(); ++j) {
        nextWayNums[j] =
            addMod(
                nextWayNums[j], multiplyMod(counts[i][target.charAt(j - 1) - 'a'], wayNums[j - 1]));
      }

      wayNums = nextWayNums;
    }

    return wayNums[target.length()];
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
