import java.util.Arrays;

class Solution {
  public int countQuadruples(String firstString, String secondString) {
    int[] minIndices = new int[26];
    Arrays.fill(minIndices, -1);
    for (int i = 0; i < firstString.length(); ++i) {
      char ch = firstString.charAt(i);
      if (minIndices[ch - 'a'] == -1) {
        minIndices[ch - 'a'] = i;
      }
    }

    int[] maxIndices = new int[26];
    Arrays.fill(maxIndices, -1);
    for (int i = secondString.length() - 1; i >= 0; --i) {
      char ch = secondString.charAt(i);
      if (maxIndices[ch - 'a'] == -1) {
        maxIndices[ch - 'a'] = i;
      }
    }

    int minDiff = Integer.MAX_VALUE;
    int count = 0;
    for (int i = 0; i < 26; ++i) {
      if (minIndices[i] != -1 && maxIndices[i] != -1) {
        int diff = minIndices[i] - maxIndices[i];
        if (diff < minDiff) {
          minDiff = diff;
          count = 1;
        } else if (diff == minDiff) {
          ++count;
        }
      }
    }

    return (minDiff == Integer.MAX_VALUE) ? 0 : count;
  }
}
