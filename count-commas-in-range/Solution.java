import java.util.stream.IntStream;

class Solution {
  public int countCommas(int n) {
    return IntStream.rangeClosed(1, n).map(x -> (String.valueOf(x).length() - 1) / 3).sum();
  }
}