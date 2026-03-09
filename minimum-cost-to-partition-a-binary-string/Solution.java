class Solution {
  public long minCost(String s, int encCost, int flatCost) {
    int[] onePrefixCounts = new int[s.length() + 1];
    for (int i = 1; i < onePrefixCounts.length; ++i) {
      onePrefixCounts[i] = onePrefixCounts[i - 1] + ((s.charAt(i - 1) == '1') ? 1 : 0);
    }

    return computeCost(s, encCost, flatCost, onePrefixCounts, 0, s.length() - 1);
  }

  long computeCost(
      String s, int encCost, int flatCost, int[] onePrefixCounts, int beginIndex, int endIndex) {
    int length = endIndex - beginIndex + 1;
    int oneCount = onePrefixCounts[endIndex + 1] - onePrefixCounts[beginIndex];

    long result = (oneCount == 0) ? flatCost : ((long) length * oneCount * encCost);

    if (length % 2 == 0) {
      result =
          Math.min(
              result,
              computeCost(
                      s,
                      encCost,
                      flatCost,
                      onePrefixCounts,
                      beginIndex,
                      beginIndex + length / 2 - 1)
                  + computeCost(
                      s, encCost, flatCost, onePrefixCounts, beginIndex + length / 2, endIndex));
    }

    return result;
  }
}