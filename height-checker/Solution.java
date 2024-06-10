import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int heightChecker(int[] heights) {
    int[] sorted = Arrays.stream(heights).sorted().toArray();

    return (int) IntStream.range(0, heights.length).filter(i -> heights[i] != sorted[i]).count();
  }
}
