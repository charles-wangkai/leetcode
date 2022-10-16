import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countDistinctIntegers(int[] nums) {
    return (int)
        Arrays.stream(nums)
            .flatMap(
                x ->
                    IntStream.of(
                        x,
                        Integer.parseInt(
                            new StringBuilder(String.valueOf(x)).reverse().toString())))
            .distinct()
            .count();
  }
}
