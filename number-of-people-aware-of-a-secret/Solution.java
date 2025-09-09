import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int peopleAwareOfSecret(int n, int delay, int forget) {
    int[] counts = new int[forget];
    counts[0] = 1;

    for (int i = 1; i < n; ++i) {
      int[] nextCounts = new int[counts.length];
      for (int j = 0; j < counts.length; ++j) {
        if (j != counts.length - 1) {
          nextCounts[j + 1] = addMod(nextCounts[j + 1], counts[j]);
        }
        if (j >= delay - 1 && j != counts.length - 1) {
          nextCounts[0] = addMod(nextCounts[0], counts[j]);
        }
      }

      counts = nextCounts;
    }

    return Arrays.stream(counts).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}