// https://leetcode.com/problems/frequencies-of-shortest-supersequences/solutions/6331981/python-brute-force/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> supersequences(String[] words) {
    List<Character> letters =
        Arrays.stream(words)
            .flatMap(word -> word.chars().mapToObj(c -> (char) c))
            .distinct()
            .toList();

    List<List<Integer>> result = new ArrayList<>();
    int[] masks =
        IntStream.range(0, 1 << letters.size())
            .boxed()
            .sorted(Comparator.comparing(Integer::bitCount).reversed())
            .mapToInt(Integer::intValue)
            .toArray();
    int prevLength = Integer.MAX_VALUE;
    for (int mask : masks) {
      boolean[] singles = new boolean[26];
      for (int i = 0; i < letters.size(); ++i) {
        if (((mask >> i) & 1) == 1) {
          singles[letters.get(i) - 'a'] = true;
        }
      }

      if (check(words, singles)) {
        int[] counts = new int[26];
        for (char letter : letters) {
          counts[letter - 'a'] = singles[letter - 'a'] ? 1 : 2;
        }

        int length = Arrays.stream(counts).sum();
        if (length != prevLength && !result.isEmpty()) {
          break;
        }

        result.add(Arrays.stream(counts).boxed().toList());

        prevLength = length;
      }
    }

    return result;
  }

  boolean check(String[] words, boolean[] singles) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[26];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (String word : words) {
      char letter1 = word.charAt(0);
      char letter2 = word.charAt(1);
      if (singles[letter1 - 'a'] && singles[letter2 - 'a']) {
        adjLists[letter1 - 'a'].add(letter2 - 'a');
      }
    }

    List<Integer> sorted = topologicalSort(adjLists);
    Map<Integer, Integer> letterToIndex =
        IntStream.range(0, sorted.size()).boxed().collect(Collectors.toMap(sorted::get, i -> i));

    for (int node = 0; node < adjLists.length; ++node) {
      for (int adj : adjLists[node]) {
        if (letterToIndex.get(adj) <= letterToIndex.get(node)) {
          return false;
        }
      }
    }

    return true;
  }

  List<Integer> topologicalSort(List<Integer>[] adjLists) {
    int n = adjLists.length;

    List<Integer> sorted = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (!visited[i]) {
        search(sorted, adjLists, visited, i);
      }
    }
    Collections.reverse(sorted);

    return sorted;
  }

  void search(List<Integer> sorted, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(sorted, adjLists, visited, adj);
      }
    }

    sorted.add(node);
  }
}