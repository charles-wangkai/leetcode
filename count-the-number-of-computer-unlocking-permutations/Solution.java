import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countPermutations(int[] complexity) {
    if (IntStream.range(1, complexity.length).anyMatch(i -> complexity[i] <= complexity[0])) {
      return 0;
    }

    return IntStream.rangeClosed(1, complexity.length - 1).reduce(this::multiplyMod).getAsInt();
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}