import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> getLongestSubsequence(String[] words, int[] groups) {
    return IntStream.range(0, groups.length)
        .filter(i -> i == 0 || groups[i] != groups[i - 1])
        .mapToObj(i -> words[i])
        .toList();
  }
}
