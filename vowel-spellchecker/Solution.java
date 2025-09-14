import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public String[] spellchecker(String[] wordlist, String[] queries) {
    Set<String> wordSet = Arrays.stream(wordlist).collect(Collectors.toSet());

    Map<String, String> lowerToWord = new HashMap<>();
    for (String word : wordlist) {
      String lower = word.toLowerCase();
      if (!lowerToWord.containsKey(lower)) {
        lowerToWord.put(lower, word);
      }
    }

    Map<String, String> patternToWord = new HashMap<>();
    for (String word : wordlist) {
      String pattern = buildPattern(word);
      if (!patternToWord.containsKey(pattern)) {
        patternToWord.put(pattern, word);
      }
    }

    return Arrays.stream(queries)
        .map(query -> match(wordSet, lowerToWord, patternToWord, query))
        .toArray(String[]::new);
  }

  static String buildPattern(String s) {
    return s.toLowerCase().replaceAll("[aeiou]", "?");
  }

  static String match(
      Set<String> wordSet,
      Map<String, String> lowerToWord,
      Map<String, String> patternToWord,
      String query) {
    if (wordSet.contains(query)) {
      return query;
    }

    String lower = query.toLowerCase();
    if (lowerToWord.containsKey(lower)) {
      return lowerToWord.get(lower);
    }

    String pattern = buildPattern(query);
    if (patternToWord.containsKey(pattern)) {
      return patternToWord.get(pattern);
    }

    return "";
  }
}
