import java.util.HashSet;
import java.util.Set;

class Solution {
  public int lengthOfLongestSubstring(String s) {
    Set<Character> seen = new HashSet<>();
    int maxLength = 0;
    int leftIndex = 0;
    for (int rightIndex = 0; rightIndex < s.length(); ++rightIndex) {
      char ch = s.charAt(rightIndex);
      while (seen.contains(ch)) {
        seen.remove(s.charAt(leftIndex));
        ++leftIndex;
      }
      seen.add(ch);
      maxLength = Math.max(maxLength, rightIndex - leftIndex + 1);
    }

    return maxLength;
  }
}
