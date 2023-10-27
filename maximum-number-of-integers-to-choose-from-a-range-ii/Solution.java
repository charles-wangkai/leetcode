import java.util.Arrays;

class Solution {
  public int maxCount(int[] banned, int n, long maxSum) {
    int maxValue = findMaxValue(banned, n, maxSum);

    return maxValue - (int) Arrays.stream(banned).distinct().filter(x -> x <= maxValue).count();
  }

  int findMaxValue(int[] banned, int n, long maxSum) {
    int result = 0;
    int lower = 1;
    int upper = n;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (middle * (middle + 1L) / 2
              - Arrays.stream(banned).distinct().filter(x -> x <= middle).asLongStream().sum()
          <= maxSum) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
