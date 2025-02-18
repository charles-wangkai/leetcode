import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public boolean maxSubstringLength(String s, int k) {
    if (k == 0) {
      return true;
    }

    Map<Character, Integer> letterToBeginIndex = new HashMap<>();
    Map<Character, Integer> letterToEndIndex = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      letterToBeginIndex.putIfAbsent(s.charAt(i), i);
      letterToEndIndex.put(s.charAt(i), i);
    }

    Map<Character, Set<Character>> letterToAdjs = new HashMap<>();
    for (char letter : letterToBeginIndex.keySet()) {
      letterToAdjs.put(letter, new HashSet<>());
      for (int i = letterToBeginIndex.get(letter); i <= letterToEndIndex.get(letter); ++i) {
        if (s.charAt(i) != letter) {
          letterToAdjs.get(letter).add(s.charAt(i));
        }
      }
    }

    List<Character> sorted = topologicalSort(letterToAdjs);
    if (computeConnectedComponentNum(letterToAdjs, sorted) == 1) {
      return false;
    }

    int partNum = 0;
    Set<Character> visited = new HashSet<>();
    for (char letter : sorted) {
      if (!visited.contains(letter)) {
        partNum += Math.max(1, search1(letterToAdjs, visited, letter));
      }
    }

    return partNum >= k;
  }

  int computeConnectedComponentNum(
      Map<Character, Set<Character>> letterToAdjs, List<Character> sorted) {
    Map<Character, List<Character>> letterToReversedAdjs = new HashMap<>();
    for (char letter : letterToAdjs.keySet()) {
      letterToReversedAdjs.put(letter, new ArrayList<>());
    }
    for (char letter : letterToAdjs.keySet()) {
      for (char adj : letterToAdjs.get(letter)) {
        letterToReversedAdjs.get(adj).add(letter);
      }
    }

    int result = 0;
    Set<Character> visited = new HashSet<>();
    for (char letter : sorted) {
      if (!visited.contains(letter)) {
        ++result;
        search2(letterToReversedAdjs, visited, letter);
      }
    }

    return result;
  }

  void search2(
      Map<Character, List<Character>> letterToReversedAdjs, Set<Character> visited, char letter) {
    visited.add(letter);

    for (char adj : letterToReversedAdjs.get(letter)) {
      if (!visited.contains(adj)) {
        search2(letterToReversedAdjs, visited, adj);
      }
    }
  }

  int search1(Map<Character, Set<Character>> letterToAdjs, Set<Character> visited, char letter) {
    visited.add(letter);

    if (letterToAdjs.get(letter).isEmpty()) {
      return 1;
    }

    int result = 0;
    for (char adj : letterToAdjs.get(letter)) {
      if (!visited.contains(adj)) {
        result += search1(letterToAdjs, visited, adj);
      }
    }

    return result;
  }

  List<Character> topologicalSort(Map<Character, Set<Character>> letterToAdjs) {
    List<Character> sorted = new ArrayList<>();
    Set<Character> visited = new HashSet<>();
    for (char letter : letterToAdjs.keySet()) {
      if (!visited.contains(letter)) {
        search(sorted, letterToAdjs, visited, letter);
      }
    }
    Collections.reverse(sorted);

    return sorted;
  }

  void search(
      List<Character> sorted,
      Map<Character, Set<Character>> letterToAdjs,
      Set<Character> visited,
      char letter) {
    visited.add(letter);

    for (char adj : letterToAdjs.get(letter)) {
      if (!visited.contains(adj)) {
        search(sorted, letterToAdjs, visited, adj);
      }
    }

    sorted.add(letter);
  }
}