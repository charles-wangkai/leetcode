import java.util.stream.IntStream;

class Solution {
  public int maximumLengthSubstring(String s) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        String substr = s.substring(beginIndex, endIndex + 1);
        if (IntStream.rangeClosed('a', 'z')
            .allMatch(c -> substr.chars().filter(ch -> ch == c).count() <= 2)) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }
}