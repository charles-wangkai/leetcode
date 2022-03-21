import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> partitionLabels(String s) {
    int[] lastIndices = new int[26];
    for (int i = 0; i < s.length(); ++i) {
      lastIndices[s.charAt(i) - 'a'] = i;
    }

    List<Integer> sizes = new ArrayList<>();
    int beginIndex = 0;
    int endIndex = -1;
    for (int i = 0; i < s.length(); ++i) {
      endIndex = Math.max(endIndex, lastIndices[s.charAt(i) - 'a']);

      if (endIndex == i) {
        sizes.add(endIndex - beginIndex + 1);
        beginIndex = endIndex + 1;
      }
    }

    return sizes;
  }
}
