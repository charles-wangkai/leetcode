import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maximumSumOfHeights(List<Integer> maxHeights) {
    return IntStream.range(0, maxHeights.size())
        .mapToLong(i -> computeHeightSum(maxHeights, i))
        .max()
        .getAsLong();
  }

  long computeHeightSum(List<Integer> maxHeights, int peakIndex) {
    int[] heights = new int[maxHeights.size()];
    heights[peakIndex] = maxHeights.get(peakIndex);
    for (int i = peakIndex - 1; i >= 0; --i) {
      heights[i] = Math.min(maxHeights.get(i), heights[i + 1]);
    }
    for (int i = peakIndex + 1; i < heights.length; ++i) {
      heights[i] = Math.min(maxHeights.get(i), heights[i - 1]);
    }

    return Arrays.stream(heights).asLongStream().sum();
  }
}
