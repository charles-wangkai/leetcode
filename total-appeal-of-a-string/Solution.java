import java.util.HashMap;
import java.util.Map;

class Solution {
  public long appealSum(String s) {
    long result = 0;
    Map<Character, Integer> letterToLastIndex = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      result += (long) (i - letterToLastIndex.getOrDefault(letter, -1)) * (s.length() - i);

      letterToLastIndex.put(letter, i);
    }

    return result;
  }
}