import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<String> maxNumOfSubstrings(String s) {
    int[] beginIndices = new int[26];
    Arrays.fill(beginIndices, Integer.MAX_VALUE);

    int[] endIndices = new int[26];
    Arrays.fill(endIndices, Integer.MIN_VALUE);

    for (int i = 0; i < s.length(); ++i) {
      int value = s.charAt(i) - 'a';
      beginIndices[value] = Math.min(beginIndices[value], i);
      endIndices[value] = Math.max(endIndices[value], i);
    }

    List<Element> elements = new ArrayList<>();
    for (int i = 0; i < 26; ++i) {
      if (beginIndices[i] != Integer.MAX_VALUE) {
        Set<Integer> values = new HashSet<>();
        values.add(i);

        int minIndex = beginIndices[i];
        int maxIndex = endIndices[i];
        int j = minIndex;
        while (j <= maxIndex) {
          int value = s.charAt(j) - 'a';
          if (!values.contains(value)) {
            values.add(value);

            if (beginIndices[value] < minIndex) {
              minIndex = beginIndices[value];
              j = minIndex;
            }

            maxIndex = Math.max(maxIndex, endIndices[value]);
          }

          ++j;
        }

        elements.add(new Element(s.substring(minIndex, maxIndex + 1), values));
      }
    }

    Collections.sort(elements, Comparator.comparing(e -> e.substring().length()));

    List<String> result = new ArrayList<>();
    Set<Integer> seen = new HashSet<>();
    for (Element element : elements) {
      if (!element.values().stream().anyMatch(seen::contains)) {
        result.add(element.substring());
        seen.addAll(element.values());
      }
    }

    return result;
  }
}

record Element(String substring, Set<Integer> values) {}