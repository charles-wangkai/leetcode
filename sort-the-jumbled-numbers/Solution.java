import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] sortJumbled(int[] mapping, int[] nums) {
    int[] mapped =
        Arrays.stream(nums)
            .map(
                x ->
                    Integer.parseInt(
                        String.valueOf(x)
                            .chars()
                            .map(c -> mapping[c - '0'])
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining())))
            .toArray();

    return IntStream.range(0, nums.length)
        .boxed()
        .sorted(Comparator.<Integer, Integer>comparing(i -> mapped[i]).thenComparing(i -> i))
        .mapToInt(i -> nums[i])
        .toArray();
  }
}