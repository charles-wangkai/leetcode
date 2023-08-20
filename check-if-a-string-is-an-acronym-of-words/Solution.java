import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public boolean isAcronym(List<String> words, String s) {
    return s.equals(
        words.stream()
            .map(word -> word.charAt(0))
            .map(String::valueOf)
            .collect(Collectors.joining()));
  }
}
