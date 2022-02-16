import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String longestWord(String[] words) {
    Set<String> set = Arrays.stream(words).collect(Collectors.toSet());

    String result = "";
    for (String word : words) {
      if (IntStream.range(1, word.length()).allMatch(i -> set.contains(word.substring(0, i)))
          && (word.length() > result.length()
              || (word.length() == result.length() && word.compareTo(result) < 0))) {
        result = word;
      }
    }

    return result;
  }
}