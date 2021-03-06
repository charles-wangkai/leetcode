import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minimumLengthEncoding(String[] words) {
    Arrays.sort(words, Comparator.comparing(String::length).reversed());

    Set<String> suffixes = new HashSet<>();
    int result = 0;
    for (String word : words) {
      if (!suffixes.contains(word)) {
        result += word.length() + 1;

        for (int i = 0; i < word.length(); ++i) {
          suffixes.add(word.substring(i));
        }
      }
    }

    return result;
  }
}
