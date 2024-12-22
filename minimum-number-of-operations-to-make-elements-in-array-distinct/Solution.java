import java.util.stream.IntStream;

class Solution {
  public int minimumOperations(int[] nums) {
    return IntStream.iterate(0, i -> i + 1)
        .filter(
            i ->
                IntStream.range(i * 3, nums.length).map(j -> nums[j]).distinct().count()
                    == Math.max(0, nums.length - i * 3))
        .findFirst()
        .getAsInt();
  }
}