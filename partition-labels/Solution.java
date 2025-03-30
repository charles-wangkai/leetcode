import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> partitionLabels(String s) {
    Map<Character, Integer> letterToLastIndex = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      letterToLastIndex.put(s.charAt(i), i);
    }

    List<Integer> result = new ArrayList<>();
    int beginIndex = 0;
    int endIndex = -1;
    for (int i = 0; i < s.length(); ++i) {
      endIndex = Math.max(endIndex, letterToLastIndex.get(s.charAt(i)));
      if (endIndex == i) {
        result.add(endIndex - beginIndex + 1);
        beginIndex = endIndex + 1;
      }
    }

    return result;
  }
}
