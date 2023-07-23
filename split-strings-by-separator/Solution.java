import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class Solution {
  public List<String> splitWordsBySeparator(List<String> words, char separator) {
    return words.stream()
        .flatMap(word -> Arrays.stream(word.split(Pattern.quote(String.valueOf(separator)))))
        .filter(s -> !s.isEmpty())
        .toList();
  }
}
