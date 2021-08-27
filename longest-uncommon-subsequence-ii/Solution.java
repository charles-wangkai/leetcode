import java.util.Arrays;

class Solution {
  public int findLUSlength(String[] strs) {
    return Arrays.stream(strs)
        .filter(s -> Arrays.stream(strs).filter(str -> isSubsequence(s, str)).count() == 1)
        .mapToInt(String::length)
        .max()
        .orElse(-1);
  }

  boolean isSubsequence(String s, String target) {
    int fromIndex = 0;
    for (int i = 0; i < s.length(); ++i) {
      int index = target.indexOf(s.charAt(i), fromIndex);
      if (index == -1) {
        return false;
      }

      fromIndex = index + 1;
    }

    return true;
  }
}
