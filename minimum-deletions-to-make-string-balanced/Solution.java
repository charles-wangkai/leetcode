import java.util.stream.IntStream;

class Solution {
  public int minimumDeletions(String s) {
    int bLeftCount = 0;
    int[] bLeftCounts = new int[s.length()];
    for (int i = 0; i < bLeftCounts.length; ++i) {
      if (s.charAt(i) == 'b') {
        ++bLeftCount;
      }
      bLeftCounts[i] = bLeftCount;
    }

    int aRightCount = 0;
    int[] aRightCounts = new int[s.length()];
    for (int i = aRightCounts.length - 1; i >= 0; --i) {
      aRightCounts[i] = aRightCount;
      if (s.charAt(i) == 'a') {
        ++aRightCount;
      }
    }

    return Math.min(
        aRightCount,
        IntStream.range(0, s.length()).map(i -> bLeftCounts[i] + aRightCounts[i]).min().getAsInt());
  }
}
