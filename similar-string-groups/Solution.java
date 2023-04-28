import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int numSimilarGroups(String[] strs) {
    Map<String, List<String>> wordToSimilars = new HashMap<>();
    for (String word : strs) {
      wordToSimilars.put(word, new ArrayList<>());
    }

    for (int i = 0; i < strs.length; ++i) {
      for (int j = i + 1; j < strs.length; ++j) {
        if (isSimilar(strs[i], strs[j])) {
          wordToSimilars.get(strs[i]).add(strs[j]);
          wordToSimilars.get(strs[j]).add(strs[i]);
        }
      }
    }

    int result = 0;
    Set<String> visited = new HashSet<>();
    for (String word : strs) {
      if (!visited.contains(word)) {
        search(wordToSimilars, visited, word);
        ++result;
      }
    }

    return result;
  }

  boolean isSimilar(String word1, String word2) {
    int[] diffIndices =
        IntStream.range(0, word1.length())
            .filter(i -> word1.charAt(i) != word2.charAt(i))
            .toArray();

    return diffIndices.length == 2
        && word2.charAt(diffIndices[0]) == word1.charAt(diffIndices[1])
        && word2.charAt(diffIndices[1]) == word1.charAt(diffIndices[0]);
  }

  void search(Map<String, List<String>> wordToSimilars, Set<String> visited, String word) {
    visited.add(word);

    for (String similar : wordToSimilars.get(word)) {
      if (!visited.contains(similar)) {
        search(wordToSimilars, visited, similar);
      }
    }
  }
}
