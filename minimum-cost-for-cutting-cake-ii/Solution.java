// https://leetcode.com/problems/minimum-cost-for-cutting-cake-ii/solutions/5473140/java-c-python-greedy/

import java.util.Arrays;

class Solution {
  public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
    Arrays.sort(horizontalCut);
    Arrays.sort(verticalCut);

    long result = 0;
    int horizontalIndex = horizontalCut.length - 1;
    int horizontalFactor = 1;
    int verticalIndex = verticalCut.length - 1;
    int verticalFactor = 1;
    while (horizontalIndex != -1 || verticalIndex != -1) {
      if (verticalIndex == -1
          || (horizontalIndex != -1
              && horizontalCut[horizontalIndex] > verticalCut[verticalIndex])) {
        result += horizontalCut[horizontalIndex] * horizontalFactor;
        ++verticalFactor;
        --horizontalIndex;
      } else {
        result += verticalCut[verticalIndex] * verticalFactor;
        ++horizontalFactor;
        --verticalIndex;
      }
    }

    return result;
  }
}