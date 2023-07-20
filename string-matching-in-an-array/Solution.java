import java.util.Arrays;
import java.util.List;

class Solution {
  public List<String> stringMatching(String[] words) {
    return Arrays.stream(words)
        .filter(word -> Arrays.stream(words).anyMatch(w -> !w.equals(word) && w.contains(word)))
        .toList();
  }
}
