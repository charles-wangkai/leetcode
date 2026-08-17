import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int stoneGameV(int[] stoneValue) {
    int[][] cache = new int[stoneValue.length][stoneValue.length];
    for (int i = 0; i < cache.length; ++i) {
      Arrays.fill(cache[i], -1);
    }

    return search(cache, stoneValue, 0, stoneValue.length - 1);
  }

  int search(int[][] cache, int[] stoneValue, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return 0;
    }

    if (cache[beginIndex][endIndex] == -1) {
      int total = IntStream.rangeClosed(beginIndex, endIndex).map(i -> stoneValue[i]).sum();

      int result = 0;
      int leftSum = 0;
      for (int i = beginIndex; i < endIndex; ++i) {
        leftSum += stoneValue[i];
        int rightSum = total - leftSum;

        if (leftSum < rightSum) {
          result = Math.max(result, leftSum + search(cache, stoneValue, beginIndex, i));
        } else if (leftSum > rightSum) {
          result = Math.max(result, rightSum + search(cache, stoneValue, i + 1, endIndex));
        } else {
          result =
              Math.max(
                  result,
                  Math.max(
                      leftSum + search(cache, stoneValue, beginIndex, i),
                      rightSum + search(cache, stoneValue, i + 1, endIndex)));
        }
      }

      cache[beginIndex][endIndex] = result;
    }

    return cache[beginIndex][endIndex];
  }
}