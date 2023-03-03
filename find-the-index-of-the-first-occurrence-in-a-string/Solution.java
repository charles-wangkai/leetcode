import java.util.stream.IntStream;

class Solution {
  public int strStr(String haystack, String needle) {
    return IntStream.rangeClosed(0, haystack.length() - needle.length())
        .filter(
            i ->
                IntStream.range(0, needle.length())
                    .allMatch(j -> needle.charAt(j) == haystack.charAt(i + j)))
        .findFirst()
        .orElse(-1);
  }
}
