import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int longestValidSubstring(String word, List<String> forbidden) {
    int maxLength = forbidden.stream().mapToInt(String::length).max().getAsInt();
    Set<String> forbiddenSet = Set.copyOf(forbidden);

    int result = 0;
    int endIndex = -1;
    for (int i = 0; i < word.length(); ++i) {
      while (endIndex != word.length() - 1
          && check(maxLength, forbiddenSet, word, i, endIndex + 1)) {
        ++endIndex;
      }

      result = Math.max(result, endIndex - i + 1);
    }

    return result;
  }

  boolean check(
      int maxLength, Set<String> forbiddenSet, String word, int beginIndex, int endIndex) {
    return IntStream.rangeClosed(1, Math.min(maxLength, endIndex - beginIndex + 1))
        .allMatch(
            length -> !forbiddenSet.contains(word.substring(endIndex + 1 - length, endIndex + 1)));
  }
}
