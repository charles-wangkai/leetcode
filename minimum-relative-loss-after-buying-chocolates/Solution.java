import java.util.Arrays;

class Solution {
  public long[] minimumRelativeLosses(int[] prices, int[][] queries) {
    Arrays.sort(prices);

    long[] leftSums = new long[prices.length + 1];
    for (int i = 1; i < leftSums.length; ++i) {
      leftSums[i] = leftSums[i - 1] + prices[i - 1];
    }

    long[] rightSums = new long[prices.length + 1];
    for (int i = 1; i < rightSums.length; ++i) {
      rightSums[i] = rightSums[i - 1] + prices[prices.length - i];
    }

    return Arrays.stream(queries)
        .mapToLong(query -> computeMinLoss(prices, leftSums, rightSums, query[0], query[1]))
        .toArray();
  }

  long computeMinLoss(int[] prices, long[] leftSums, long[] rightSums, int k, int m) {
    int leftNum = findLeftNum(prices, k);
    int rightNum = prices.length - leftNum;

    int left = -1;
    int lower = 0;
    int upper = Math.min(leftNum, m);
    while (true) {
      int middle = (lower + upper) / 2;
      if (isTooFewLeft(prices, k, leftNum, rightNum, middle, m - middle)) {
        lower = middle + 1;
      } else if (isTooManyLeft(prices, k, leftNum, rightNum, middle, m - middle)) {
        upper = middle - 1;
      } else {
        left = middle;

        break;
      }
    }

    int right = m - left;

    return leftSums[left] + (2L * k * right - rightSums[right]);
  }

  boolean isTooFewLeft(int[] prices, int k, int leftNum, int rightNum, int left, int right) {
    return right != 0 && left != leftNum && prices[left] < 2 * k - prices[prices.length - right];
  }

  boolean isTooManyLeft(int[] prices, int k, int leftNum, int rightNum, int left, int right) {
    return left != 0
        && right != rightNum
        && 2 * k - prices[prices.length - right - 1] < prices[left - 1];
  }

  int findLeftNum(int[] prices, int k) {
    int result = 0;
    int lower = 1;
    int upper = prices.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (prices[middle - 1] <= k) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
