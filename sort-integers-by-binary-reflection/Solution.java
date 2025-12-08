import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public int[] sortByReflection(int[] nums) {
    Map<Integer, Integer> valueToReflection =
        Arrays.stream(nums)
            .distinct()
            .boxed()
            .collect(
                Collectors.toMap(
                    x -> x,
                    x ->
                        Integer.parseInt(
                            new StringBuilder(Integer.toBinaryString(x)).reverse().toString(), 2)));

    return Arrays.stream(nums)
        .boxed()
        .sorted(
            Comparator.<Integer, Integer>comparing(valueToReflection::get).thenComparing(x -> x))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}