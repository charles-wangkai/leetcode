import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maxSubstringLength(String s) {
    Map<Character, List<Integer>> letterToIndices = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      letterToIndices.putIfAbsent(letter, new ArrayList<>());
      letterToIndices.get(letter).add(i);
    }

    int result = -1;
    for (char beginLetter : letterToIndices.keySet()) {
      int beginIndex = letterToIndices.get(beginLetter).get(0);
      for (char endLetter : letterToIndices.keySet()) {
        int endIndex =
            letterToIndices.get(endLetter).get(letterToIndices.get(endLetter).size() - 1);
        if (endIndex >= beginIndex
            && (beginIndex != 0 || endIndex != s.length() - 1)
            && letterToIndices.values().stream()
                .allMatch(indices -> isSelfContained(indices, beginIndex, endIndex))) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }

  boolean isSelfContained(List<Integer> indices, int beginIndex, int endIndex) {
    if (indices.get(0) >= beginIndex && indices.get(indices.size() - 1) <= endIndex) {
      return true;
    }

    int beforeIndex = Collections.binarySearch(indices, beginIndex);
    if (beforeIndex >= 0) {
      return false;
    }
    beforeIndex = -beforeIndex - 2;

    return beforeIndex == indices.size() - 1 || indices.get(beforeIndex + 1) > endIndex;
  }
}