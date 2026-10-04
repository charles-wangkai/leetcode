import java.util.stream.IntStream;

class Solution {
  public int minRotations(String s) {
    return IntStream.range(0, s.length())
        .map(
            i -> {
              int distance = Math.abs(s.charAt(i) - ((i == 0) ? '0' : s.charAt(i - 1)));

              return Math.min(distance, 10 - distance);
            })
        .sum();
  }
}