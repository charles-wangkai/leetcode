import java.util.stream.IntStream;

class Solution {
  public boolean areSimilar(int[][] mat, int k) {
    return IntStream.range(0, mat.length)
        .allMatch(r -> isSameAfterShift(mat[r], (r % 2 == 0) ? k : -k));
  }

  boolean isSameAfterShift(int[] a, int offset) {
    return IntStream.range(0, a.length)
        .allMatch(i -> a[i] == a[Math.floorMod(i + offset, a.length)]);
  }
}