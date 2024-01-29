import java.util.stream.IntStream;

class Solution {
  public int countKeyChanges(String s) {
    return (int)
        IntStream.range(0, s.length() - 1)
            .filter(
                i -> Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(i + 1)))
            .count();
  }
}