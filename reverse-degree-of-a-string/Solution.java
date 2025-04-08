import java.util.stream.IntStream;

class Solution {
  public int reverseDegree(String s) {
    return IntStream.range(0, s.length()).map(i -> (i + 1) * (26 - (s.charAt(i) - 'a'))).sum();
  }
}