import java.util.stream.IntStream;

class Solution {
  public boolean hasSpecialSubstring(String s, int k) {
    return IntStream.rangeClosed(0, s.length() - k)
        .anyMatch(
            i ->
                (i == 0 || s.charAt(i) != s.charAt(i - 1))
                    && (i + k == s.length() || s.charAt(i + k) != s.charAt(i + k - 1))
                    && IntStream.range(i, i + k).map(s::charAt).distinct().count() == 1);
  }
}