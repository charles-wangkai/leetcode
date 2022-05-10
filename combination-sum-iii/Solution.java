import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
    return IntStream.range(0, 1 << 9)
        .mapToObj(
            code ->
                IntStream.rangeClosed(1, 9)
                    .filter(i -> (code & (1 << (i - 1))) != 0)
                    .boxed()
                    .collect(Collectors.toList()))
        .filter(
            combination ->
                combination.size() == k && combination.stream().mapToInt(x -> x).sum() == n)
        .collect(Collectors.toList());
  }
}
