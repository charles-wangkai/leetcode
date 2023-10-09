import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(String s1, String s2, int x) {
    int[] diffIndices =
        IntStream.range(0, s1.length()).filter(i -> s1.charAt(i) != s2.charAt(i)).toArray();
    if (diffIndices.length % 2 == 1) {
      return -1;
    }

    int[][] dp = new int[diffIndices.length][diffIndices.length];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    for (int length = 2; length <= diffIndices.length; length += 2) {
      for (int leftIndex = 0; leftIndex + length <= diffIndices.length; ++leftIndex) {
        int rightIndex = leftIndex + length - 1;
        for (int i = leftIndex + 1; i <= rightIndex; i += 2) {
          dp[leftIndex][rightIndex] =
              Math.min(
                  dp[leftIndex][rightIndex],
                  getCost(diffIndices, leftIndex, i, x)
                      + getValue(dp, leftIndex + 1, i - 1)
                      + getValue(dp, i + 1, rightIndex));
        }
      }
    }

    return getValue(dp, 0, diffIndices.length - 1);
  }

  int getCost(int[] diffIndices, int index1, int index2, int x) {
    return Math.min(x, diffIndices[index2] - diffIndices[index1]);
  }

  int getValue(int[][] dp, int beginIndex, int endIndex) {
    return (beginIndex <= endIndex) ? dp[beginIndex][endIndex] : 0;
  }
}
