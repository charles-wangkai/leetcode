import java.util.Collections;
import java.util.stream.IntStream;

class Solution {
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    int result = -1;
    int lower = 0;
    int upper = heights.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(heights, bricks, ladders, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] heights, int bricks, int ladders, int endIndex) {
    return IntStream.range(0, endIndex)
            .map(i -> heights[i + 1] - heights[i])
            .filter(gap -> gap > 0)
            .boxed()
            .sorted(Collections.reverseOrder())
            .skip(ladders)
            .mapToInt(Integer::intValue)
            .asLongStream()
            .sum()
        <= bricks;
  }
}
