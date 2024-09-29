import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  static final String VOWELS = "aeiou";

  public long countOfSubstrings(String word, int k) {
    Map<Character, Queue<Integer>> vowelToIndices = new HashMap<>();
    for (char vowel : VOWELS.toCharArray()) {
      vowelToIndices.put(vowel, new ArrayDeque<>());
    }
    List<Integer> consonantIndices = new ArrayList<>();
    for (int i = 0; i < word.length(); ++i) {
      char c = word.charAt(i);
      if (VOWELS.indexOf(c) == -1) {
        consonantIndices.add(i);
      } else {
        vowelToIndices.get(c).offer(i);
      }
    }

    long result = 0;
    int index = 0;
    for (int i = 0; i < word.length(); ++i) {
      for (Queue<Integer> indices : vowelToIndices.values()) {
        if (!indices.isEmpty() && indices.peek() == i - 1) {
          indices.poll();
        }
      }

      if (index != consonantIndices.size() && consonantIndices.get(index) == i - 1) {
        ++index;
      }

      int minIndex =
          vowelToIndices.values().stream()
              .mapToInt(indices -> indices.isEmpty() ? Integer.MAX_VALUE : indices.peek())
              .max()
              .getAsInt();
      if (minIndex != Integer.MAX_VALUE) {
        int maxIndex;
        if (k == 0) {
          maxIndex =
              ((index == consonantIndices.size()) ? word.length() : consonantIndices.get(index))
                  - 1;
        } else if (index + k <= consonantIndices.size()) {
          minIndex = Math.max(minIndex, consonantIndices.get(index + k - 1));
          maxIndex =
              ((index + k == consonantIndices.size())
                      ? word.length()
                      : consonantIndices.get(index + k))
                  - 1;
        } else {
          maxIndex = -1;
        }

        result += Math.max(0, maxIndex - minIndex + 1);
      }
    }

    return result;
  }
}