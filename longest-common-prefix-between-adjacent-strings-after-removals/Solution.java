import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] longestCommonPrefix(String[] words) {
    SortedMap<Integer, Integer> commonLengthToCount = new TreeMap<>();
    for (int i = 0; i < words.length - 1; ++i) {
      update(commonLengthToCount, computeCommonLength(words[i], words[i + 1]), 1);
    }

    int[] result = new int[words.length];
    for (int i = 0; i < result.length; ++i) {
      if (i != 0) {
        update(commonLengthToCount, computeCommonLength(words[i - 1], words[i]), -1);
      }
      if (i != result.length - 1) {
        update(commonLengthToCount, computeCommonLength(words[i], words[i + 1]), -1);
      }
      if (i != 0 && i != result.length - 1) {
        update(commonLengthToCount, computeCommonLength(words[i - 1], words[i + 1]), 1);
      }

      result[i] = commonLengthToCount.isEmpty() ? 0 : commonLengthToCount.lastKey();

      if (i != 0 && i != result.length - 1) {
        update(commonLengthToCount, computeCommonLength(words[i - 1], words[i + 1]), -1);
      }
      if (i != result.length - 1) {
        update(commonLengthToCount, computeCommonLength(words[i], words[i + 1]), 1);
      }
      if (i != 0) {
        update(commonLengthToCount, computeCommonLength(words[i - 1], words[i]), 1);
      }
    }

    return result;
  }

  int computeCommonLength(String s1, String s2) {
    int result = 0;
    while (result != s1.length()
        && result != s2.length()
        && s1.charAt(result) == s2.charAt(result)) {
      ++result;
    }

    return result;
  }

  void update(SortedMap<Integer, Integer> commonLengthToCount, int commonLength, int delta) {
    commonLengthToCount.put(
        commonLength, commonLengthToCount.getOrDefault(commonLength, 0) + delta);
    commonLengthToCount.remove(commonLength, 0);
  }
}