import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public String alienOrder(String[] words) {
    Map<Character, List<Character>> letter2adjacents = buildAdjacentsList(words);
    return topologicalSort(letter2adjacents);
  }

  Map<Character, List<Character>> buildAdjacentsList(String[] words) {
    Map<Character, List<Character>> letter2adjacents = initLetter2adjacents(words);
    for (int i = 0; i < words.length - 1; i++) {
      String word1 = words[i];
      String word2 = words[i + 1];
      int diffIndex = 0;
      while (diffIndex < word1.length()
          && diffIndex < word2.length()
          && word1.charAt(diffIndex) == word2.charAt(diffIndex)) {
        diffIndex++;
      }
      if (diffIndex < word1.length()) {
        if (diffIndex == word2.length()) {
          return null;
        } else {
          letter2adjacents.get(word1.charAt(diffIndex)).add(word2.charAt(diffIndex));
        }
      }
    }
    return letter2adjacents;
  }

  Map<Character, List<Character>> initLetter2adjacents(String[] words) {
    Map<Character, List<Character>> letter2adjacents = new HashMap<>();
    for (String word : words) {
      for (int i = 0; i < word.length(); i++) {
        char letter = word.charAt(i);
        if (!letter2adjacents.containsKey(letter)) {
          letter2adjacents.put(letter, new ArrayList<>());
        }
      }
    }
    return letter2adjacents;
  }

  String topologicalSort(Map<Character, List<Character>> letter2adjacents) {
    if (letter2adjacents == null) {
      return "";
    }

    Map<Character, Integer> letter2degree = buildLetter2degree(letter2adjacents);

    Map<Character, Boolean> letter2visited = new HashMap<>();
    for (char letter : letter2degree.keySet()) {
      letter2visited.put(letter, false);
    }

    StringBuilder order = new StringBuilder();
    for (char letter : letter2degree.keySet()) {
      if (!letter2visited.get(letter) && letter2degree.get(letter) == 0) {
        dfs(letter2adjacents, letter2visited, letter2degree, order, letter);
      }
    }

    return order.length() == letter2adjacents.size() ? order.toString() : "";
  }

  Map<Character, Integer> buildLetter2degree(Map<Character, List<Character>> letter2adjacents) {
    Map<Character, Integer> letter2degree = new HashMap<>();
    for (char letter : letter2adjacents.keySet()) {
      letter2degree.put(letter, 0);
    }
    for (List<Character> adjacents : letter2adjacents.values()) {
      for (char adjacent : adjacents) {
        letter2degree.put(adjacent, letter2degree.get(adjacent) + 1);
      }
    }
    return letter2degree;
  }

  void dfs(
      Map<Character, List<Character>> letter2adjacents,
      Map<Character, Boolean> letter2visited,
      Map<Character, Integer> letter2degree,
      StringBuilder order,
      char letter) {
    letter2visited.put(letter, true);
    order.append(letter);

    for (char adjacent : letter2adjacents.get(letter)) {
      if (!letter2visited.get(adjacent)) {
        letter2degree.put(adjacent, letter2degree.get(adjacent) - 1);
        if (letter2degree.get(adjacent) == 0) {
          dfs(letter2adjacents, letter2visited, letter2degree, order, adjacent);
        }
      }
    }
  }
}
