import java.util.HashMap;
import java.util.Map;

public class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int maxLength = 0;
    int back = 0;
    Map<Character, Integer> letter2count = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char letter = s.charAt(i);
      if (!letter2count.containsKey(letter)) {
        letter2count.put(letter, 0);
      }
      letter2count.put(letter, letter2count.get(letter) + 1);
      while (letter2count.size() > 2) {
        char backLetter = s.charAt(back);
        letter2count.put(backLetter, letter2count.get(backLetter) - 1);
        if (letter2count.get(backLetter) == 0) {
          letter2count.remove(backLetter);
        }
        back++;
      }
      maxLength = Math.max(maxLength, i - back + 1);
    }
    return maxLength;
  }
}
