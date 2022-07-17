import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
    return Arrays.stream(queries)
        .mapToInt(
            query ->
                IntStream.range(0, nums.length)
                    .boxed()
                    .sorted(
                        Comparator.comparing(
                                (Integer i) -> nums[i].substring(nums[i].length() - query[1]))
                            .thenComparing(i -> i))
                    .skip(query[0] - 1)
                    .mapToInt(x -> x)
                    .findFirst()
                    .getAsInt())
        .toArray();
  }
}