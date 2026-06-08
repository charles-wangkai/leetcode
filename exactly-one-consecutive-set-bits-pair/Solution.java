import java.util.stream.IntStream;

class Solution {
  public boolean consecutiveSetBits(int n) {
    String s = Integer.toBinaryString(n);

    return IntStream.range(0, s.length() - 1)
            .filter(i -> s.charAt(i) == '1' && s.charAt(i + 1) == '1')
            .count()
        == 1;
  }
}