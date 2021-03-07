
class Solution {
  public int beautySum(String s) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      int[] counts = new int[26];
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        ++counts[s.charAt(endIndex) - 'a'];

        int minCount = Integer.MAX_VALUE;
        int maxCount = Integer.MIN_VALUE;
        for (int count : counts) {
          if (count != 0) {
            minCount = Math.min(minCount, count);
          }
          maxCount = Math.max(maxCount, count);
        }
        result += maxCount - minCount;
      }
    }

    return result;
  }
}
