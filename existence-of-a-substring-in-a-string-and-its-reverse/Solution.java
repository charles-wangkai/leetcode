import java.util.stream.IntStream;

class Solution {
  public boolean isSubstringPresent(String s) {
    return IntStream.range(0, s.length() - 1)
        .anyMatch(i -> s.contains(String.format("%c%c", s.charAt(i + 1), s.charAt(i))));
  }
}