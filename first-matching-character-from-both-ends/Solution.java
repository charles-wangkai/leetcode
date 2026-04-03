import java.util.stream.IntStream;

class Solution {
  public int firstMatchingIndex(String s) {
    return IntStream.range(0, s.length())
        .filter(i -> s.charAt(i) == s.charAt(s.length() - i - 1))
        .min()
        .orElse(-1);
  }
}