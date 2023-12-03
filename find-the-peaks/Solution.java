import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findPeaks(int[] mountain) {
    return IntStream.range(1, mountain.length - 1)
        .filter(i -> mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1])
        .boxed()
        .toList();
  }
}