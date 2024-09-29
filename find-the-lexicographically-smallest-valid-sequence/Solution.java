import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[] validSequence(String word1, String word2) {
    int[] lastIndices = buildLastIndices(word1, word2);

    List<Integer> result = new ArrayList<>();
    int index = 0;
    while (lastIndices[result.size() + 1] < index + 1) {
      while (index != word1.length() && word1.charAt(index) != word2.charAt(result.size())) {
        ++index;
      }
      if (index == word1.length()) {
        return new int[0];
      }

      result.add(index);
      ++index;
    }

    boolean changed = word1.charAt(index) != word2.charAt(result.size());
    result.add(index);
    ++index;
    while (result.size() != word2.length()) {
      while (changed && word1.charAt(index) != word2.charAt(result.size())) {
        ++index;
      }

      changed |= word1.charAt(index) != word2.charAt(result.size());
      result.add(index);
      ++index;
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int[] buildLastIndices(String word1, String word2) {
    int[] result = new int[word2.length() + 1];
    Arrays.fill(result, -1);
    result[word2.length()] = Integer.MAX_VALUE;
    int index = word2.length() - 1;
    for (int i = word1.length() - 1; i >= 0; --i) {
      if (index != -1 && word1.charAt(i) == word2.charAt(index)) {
        result[index] = i;
        --index;
      }
    }

    return result;
  }
}