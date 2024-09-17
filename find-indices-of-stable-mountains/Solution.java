import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> stableMountains(int[] height, int threshold) {
    return IntStream.range(1, height.length)
        .filter(i -> height[i - 1] > threshold)
        .boxed()
        .toList();
  }
}