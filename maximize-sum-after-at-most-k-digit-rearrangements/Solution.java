import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class Solution {
  public long maximizeSum(int[] nums, int k) {
    return Arrays.stream(nums).asLongStream().sum()
        + Arrays.stream(nums)
            .mapToLong(
                x ->
                    Long.parseLong(
                            String.valueOf(x)
                                .chars()
                                .boxed()
                                .sorted(Comparator.reverseOrder())
                                .mapToInt(Integer::intValue)
                                .mapToObj(c -> (char) c)
                                .map(String::valueOf)
                                .collect(Collectors.joining()))
                        - x)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToLong(Long::longValue)
            .limit(k)
            .sum();
  }
}