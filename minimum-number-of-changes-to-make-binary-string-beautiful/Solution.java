import java.util.stream.IntStream;

class Solution {
  public int minChanges(String s) {
    return (int)
        IntStream.range(0, s.length() / 2)
            .filter(i -> s.charAt(i * 2) != s.charAt(i * 2 + 1))
            .count();
  }
}
