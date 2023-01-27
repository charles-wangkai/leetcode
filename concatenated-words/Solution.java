import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    Arrays.sort(words, Comparator.comparing(String::length));

    List<String> result = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    for (int i = 0; i < words.length; ++i) {
      if (canForm(words[i], seen)) {
        result.add(words[i]);
      }

      seen.add(words[i]);
    }

    return result;
  }

  boolean canForm(String word, Set<String> seen) {
    boolean[] dp = new boolean[word.length() + 1];
    dp[0] = true;
    for (int i = 1; i < dp.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (dp[j] && seen.contains(word.substring(j, i))) {
          dp[i] = true;
        }
      }
    }

    return dp[word.length()];
  }
}
