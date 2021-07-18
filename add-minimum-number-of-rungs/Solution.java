import java.util.stream.IntStream;

class Solution {
  public int addRungs(int[] rungs, int dist) {
    return IntStream.range(0, rungs.length)
        .map(i -> (rungs[i] - ((i == 0) ? 0 : rungs[i - 1]) - 1) / dist)
        .sum();
  }
}
