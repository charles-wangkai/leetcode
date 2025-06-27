// https://leetcode.com/problems/longest-subsequence-repeated-k-times/discuss/1471930/Python-Answer-is-not-so-long-explained

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public String longestSubsequenceRepeatedK(String s, int k) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    String hot =
        letterToCount.keySet().stream()
            .map(letter -> String.valueOf(letter).repeat(letterToCount.get(letter) / k))
            .collect(Collectors.joining());

    Set<String> candidates = new HashSet<>();
    search(candidates, hot, 0, new StringBuilder());

    List<String> sortedCandidates =
        candidates.stream()
            .sorted(
                Comparator.comparing(String::length)
                    .reversed()
                    .thenComparing(Comparator.reverseOrder()))
            .collect(Collectors.toList());

    for (int i = 0; ; ++i) {
      if (isSubsequence(s, sortedCandidates.get(i).repeat(k))) {
        return sortedCandidates.get(i);
      }
    }
  }

  void search(Set<String> candidates, String hot, int index, StringBuilder current) {
    if (index == hot.length()) {
      permute(candidates, current, 0);

      return;
    }

    search(candidates, hot, index + 1, current);

    current.append(hot.charAt(index));
    search(candidates, hot, index + 1, current);
    current.deleteCharAt(current.length() - 1);
  }

  void permute(Set<String> candidates, StringBuilder current, int index) {
    if (index == current.length()) {
      candidates.add(current.toString());
    }

    for (int i = index; i < current.length(); ++i) {
      swap(current, i, index);
      permute(candidates, current, index + 1);
      swap(current, i, index);
    }
  }

  void swap(StringBuilder str, int index1, int index2) {
    char temp = str.charAt(index1);
    str.setCharAt(index1, str.charAt(index2));
    str.setCharAt(index2, temp);
  }

  boolean isSubsequence(String s, String sub) {
    int fromIndex = 0;
    for (char letter : sub.toCharArray()) {
      int index = s.indexOf(letter, fromIndex);
      if (index == -1) {
        return false;
      }

      fromIndex = index + 1;
    }

    return true;
  }
}
