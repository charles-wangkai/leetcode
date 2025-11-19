import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int findFinalValue(int[] nums, int original) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    return IntStream.iterate(original, x -> x * 2)
        .filter(x -> !set.contains(x))
        .findFirst()
        .getAsInt();
  }
}