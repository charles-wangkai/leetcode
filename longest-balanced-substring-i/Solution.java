class Solution {
  public int longestBalanced(String s) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      int[] counts = new int[26];
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        ++counts[s.charAt(endIndex) - 'a'];

        if (check(counts)) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }

  boolean check(int[] counts) {
    int target = -1;
    for (int count : counts) {
      if (count != 0) {
        if (target == -1) {
          target = count;
        } else if (count != target) {
          return false;
        }
      }
    }

    return true;
  }
}