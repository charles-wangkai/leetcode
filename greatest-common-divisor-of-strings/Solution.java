import java.util.stream.IntStream;

class Solution {
  public String gcdOfStrings(String str1, String str2) {
    return str1.substring(
        0,
        IntStream.rangeClosed(1, Math.min(str1.length(), str2.length()))
            .filter(
                length ->
                    str1.substring(0, length).equals(str2.substring(0, length))
                        && isDivide(str1, length)
                        && isDivide(str2, length))
            .max()
            .orElse(0));
  }

  boolean isDivide(String s, int length) {
    return s.length() % length == 0
        && IntStream.range(0, s.length()).allMatch(i -> s.charAt(i) == s.charAt(i % length));
  }
}
