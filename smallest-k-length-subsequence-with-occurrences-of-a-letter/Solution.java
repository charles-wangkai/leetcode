import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public String smallestSubsequence(String s, int k, char letter, int repetition) {
    int[] rightNums = new int[s.length()];
    for (int i = rightNums.length - 1; i >= 0; --i) {
      rightNums[i] =
          ((i == rightNums.length - 1) ? 0 : rightNums[i + 1]) + ((s.charAt(i) == letter) ? 1 : 0);
    }

    Map<Character, List<Integer>> letterToIndices = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (!letterToIndices.containsKey(c)) {
        letterToIndices.put(c, new ArrayList<>());
      }
      letterToIndices.get(c).add(i);
    }

    StringBuilder result = new StringBuilder();
    int fromIndex = 0;
    for (int i = 0; i < k; ++i) {
      for (char c = 'a'; ; ++c) {
        if (c == letter || k - i != repetition) {
          List<Integer> indices = letterToIndices.getOrDefault(c, List.of());
          int pos = Collections.binarySearch(indices, fromIndex);
          if (pos < 0) {
            pos = -1 - pos;
          }
          if (pos != indices.size()) {
            int index = indices.get(pos);
            if (rightNums[index] >= repetition && s.length() - index >= k - i) {
              if (c == letter) {
                --repetition;
              }
              result.append(c);
              fromIndex = index + 1;

              break;
            }
          }
        }
      }
    }

    return result.toString();
  }
}
