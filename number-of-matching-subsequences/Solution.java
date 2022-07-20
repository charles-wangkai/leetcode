import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public int numMatchingSubseq(String s, String[] words) {
    Map<Character, List<Integer>> letterToIndices = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      letterToIndices.putIfAbsent(letter, new ArrayList<>());
      letterToIndices.get(letter).add(i);
    }

    return (int)
        Arrays.stream(words)
            .filter(
                word -> {
                  int beginIndex = 0;
                  for (char c : word.toCharArray()) {
                    List<Integer> indices = letterToIndices.getOrDefault(c, List.of());
                    int index = Collections.binarySearch(indices, beginIndex);
                    if (index < 0) {
                      index = -1 - index;
                    }
                    if (index == indices.size()) {
                      return false;
                    }

                    beginIndex = indices.get(index) + 1;
                  }

                  return true;
                })
            .count();
  }
}