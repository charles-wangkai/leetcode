import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
    int n = words.length;

    int[] lengths = new int[n];
    Arrays.fill(lengths, 1);

    int[] prevIndices = new int[n];
    Arrays.fill(prevIndices, -1);

    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (words[j].length() == words[i].length()
            && groups[j] != groups[i]
            && computeHammingDistance(words[i], words[j]) == 1
            && lengths[j] + 1 > lengths[i]) {
          lengths[i] = lengths[j] + 1;
          prevIndices[i] = j;
        }
      }
    }

    int maxLength = Arrays.stream(lengths).max().getAsInt();
    int index =
        IntStream.range(0, lengths.length)
            .filter(i -> lengths[i] == maxLength)
            .findAny()
            .getAsInt();
    List<Integer> indices = new ArrayList<>();
    while (index != -1) {
      indices.add(index);
      index = prevIndices[index];
    }
    Collections.reverse(indices);

    return indices.stream().map(idx -> words[idx]).toList();
  }

  int computeHammingDistance(String word1, String word2) {
    return (int)
        IntStream.range(0, word1.length()).filter(i -> word1.charAt(i) != word2.charAt(i)).count();
  }
}
