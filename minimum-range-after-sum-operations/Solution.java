import java.util.stream.IntStream;

class Solution {
  public int minRange(int a, int b, int c) {
    int[] sorted = IntStream.of(a, b, c).sorted().toArray();

    return Math.min(sorted[2] - sorted[0], sorted[1]);
  }
}