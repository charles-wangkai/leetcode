import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int miceAndCheese(int[] reward1, int[] reward2, int k) {
    return Arrays.stream(reward2).sum()
        + IntStream.range(0, reward1.length)
            .map(i -> reward1[i] - reward2[i])
            .sorted()
            .skip(reward1.length - k)
            .sum();
  }
}
