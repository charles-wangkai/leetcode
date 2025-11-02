import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findMissingElements(int[] nums) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return IntStream.rangeClosed(
            Arrays.stream(nums).min().getAsInt(), Arrays.stream(nums).max().getAsInt())
        .filter(x -> !set.contains(x))
        .boxed()
        .toList();
  }
}