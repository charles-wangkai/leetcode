import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minSumOfLengths(int[] arr, int target) {
    int n = arr.length;

    Map<Integer, Integer> leftSumToIndex = new HashMap<>();
    leftSumToIndex.put(0, -1);
    int leftSum = 0;
    int[] leftMinLengths = new int[n];
    for (int i = 0; i < n; ++i) {
      leftSum += arr[i];

      leftMinLengths[i] = (i == 0) ? Integer.MAX_VALUE : leftMinLengths[i - 1];
      if (leftSumToIndex.containsKey(leftSum - target)) {
        leftMinLengths[i] = Math.min(leftMinLengths[i], i - leftSumToIndex.get(leftSum - target));
      }

      leftSumToIndex.put(leftSum, i);
    }

    Map<Integer, Integer> rightSumToIndex = new HashMap<>();
    rightSumToIndex.put(0, n);
    int rightSum = 0;
    int[] rightMinLengths = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      rightSum += arr[i];

      rightMinLengths[i] = (i == n - 1) ? Integer.MAX_VALUE : rightMinLengths[i + 1];
      if (rightSumToIndex.containsKey(rightSum - target)) {
        rightMinLengths[i] =
            Math.min(rightMinLengths[i], rightSumToIndex.get(rightSum - target) - i);
      }

      rightSumToIndex.put(rightSum, i);
    }

    return IntStream.range(0, n - 1)
        .filter(
            i ->
                leftMinLengths[i] != Integer.MAX_VALUE
                    && rightMinLengths[i + 1] != Integer.MAX_VALUE)
        .map(i -> leftMinLengths[i] + rightMinLengths[i + 1])
        .min()
        .orElse(-1);
  }
}