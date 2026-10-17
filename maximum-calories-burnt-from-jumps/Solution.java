import java.util.Arrays;

class Solution {
  public long maxCaloriesBurnt(int[] heights) {
    Arrays.sort(heights);

    long result = 0;
    int prevHeight = 0;
    int leftIndex = 0;
    int rightIndex = heights.length - 1;
    for (int i = 0; i < heights.length; ++i) {
      int height;
      if (i % 2 == 0) {
        height = heights[rightIndex];
        --rightIndex;
      } else {
        height = heights[leftIndex];
        ++leftIndex;
      }

      result += (long) (height - prevHeight) * (height - prevHeight);
      prevHeight = height;
    }

    return result;
  }
}