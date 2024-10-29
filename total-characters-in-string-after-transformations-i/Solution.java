import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int lengthAfterTransformations(String s, int t) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    for (int i = 0; i < t; ++i) {
      int[] nextCounts = new int[counts.length];
      for (int j = 0; j < counts.length - 1; ++j) {
        nextCounts[j + 1] = addMod(nextCounts[j + 1], counts[j]);
      }
      nextCounts[0] = addMod(nextCounts[0], counts[counts.length - 1]);
      nextCounts[1] = addMod(nextCounts[1], counts[counts.length - 1]);

      counts = nextCounts;
    }

    return Arrays.stream(counts).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}