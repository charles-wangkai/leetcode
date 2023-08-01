import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> combine(int n, int k) {
    return IntStream.range(0, 1 << n)
        .filter(mask -> Integer.bitCount(mask) == k)
        .mapToObj(
            mask ->
                IntStream.range(0, n)
                    .filter(i -> ((mask >> i) & 1) == 1)
                    .map(i -> i + 1)
                    .boxed()
                    .toList())
        .toList();
  }
}
