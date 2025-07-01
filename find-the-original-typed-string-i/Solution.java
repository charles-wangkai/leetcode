import java.util.stream.IntStream;

class Solution {
  public int possibleStringCount(String word) {
    return (int)
            IntStream.range(1, word.length())
                .filter(i -> word.charAt(i) == word.charAt(i - 1))
                .count()
        + 1;
  }
}