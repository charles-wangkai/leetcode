import java.util.stream.IntStream;

class Solution {
  public boolean isAdjacentDiffAtMostTwo(String s) {
    return IntStream.range(0, s.length() - 1)
        .allMatch(i -> Math.abs(s.charAt(i) - s.charAt(i + 1)) <= 2);
  }
}