import java.util.stream.IntStream;

class Solution {
  public int trap(int[] height) {
    int n = height.length;

    int[] leftMaxs = new int[n];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? 0 : leftMaxs[i - 1], height[i]);
    }

    int[] rightMaxs = new int[n];
    for (int i = rightMaxs.length - 1; i >= 0; --i) {
      rightMaxs[i] = Math.max((i == rightMaxs.length - 1) ? 0 : rightMaxs[i + 1], height[i]);
    }

    return IntStream.range(0, n).map(i -> Math.min(leftMaxs[i], rightMaxs[i]) - height[i]).sum();
  }
}
