import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int missingMultiple(int[] nums, int k) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return IntStream.iterate(k, x -> x + k).filter(x -> !set.contains(x)).findFirst().getAsInt();
  }
}