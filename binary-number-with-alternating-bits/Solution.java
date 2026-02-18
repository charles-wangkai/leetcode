import java.util.stream.IntStream;

class Solution {
  public boolean hasAlternatingBits(int n) {
    String s = Integer.toBinaryString(n);

    return IntStream.range(0, s.length() - 1).allMatch(i -> s.charAt(i) != s.charAt(i + 1));
  }
}
