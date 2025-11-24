import java.util.stream.IntStream;

class Solution {
  public int minimumFlips(int n) {
    String s = Integer.toBinaryString(n);

    return (int)
            IntStream.range(0, s.length() / 2)
                .filter(i -> s.charAt(i) != s.charAt(s.length() - 1 - i))
                .count()
        * 2;
  }
}