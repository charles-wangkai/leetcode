import java.util.Arrays;

class Solution {
  public int[] shortestToChar(String s, char c) {
    int[] result = new int[s.length()];
    Arrays.fill(result, Integer.MAX_VALUE);

    int leftIndex = -1;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == c) {
        leftIndex = i;
      }

      if (leftIndex != -1) {
        result[i] = Math.min(result[i], i - leftIndex);
      }
    }

    int rightIndex = -1;
    for (int i = s.length() - 1; i >= 0; --i) {
      if (s.charAt(i) == c) {
        rightIndex = i;
      }

      if (rightIndex != -1) {
        result[i] = Math.min(result[i], rightIndex - i);
      }
    }

    return result;
  }
}
