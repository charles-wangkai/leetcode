import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int longestBalanced(String s) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      Map<Character, Integer> letterToCount = new HashMap<>();
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        letterToCount.put(
            s.charAt(endIndex), letterToCount.getOrDefault(s.charAt(endIndex), 0) + 1);

        if (check(List.copyOf(letterToCount.values()))) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }

  boolean check(List<Integer> counts) {
    for (int i = 0; i < counts.size() - 1; ++i) {
      if (!counts.get(i).equals(counts.get(i + 1))) {
        return false;
      }
    }

    return true;
  }
}