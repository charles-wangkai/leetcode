import java.util.stream.IntStream;

class Solution {
  public int countRotations(String s, int k) {
    return (int)
        IntStream.range(0, s.length())
            .filter(i -> computeScore(s.substring(i + 1) + s.substring(0, i + 1)) == k)
            .count();
  }

  int computeScore(String str) {
    return (int)
        IntStream.range(0, str.length() - 1)
            .filter(i -> str.charAt(i) == str.charAt(i + 1))
            .count();
  }
}