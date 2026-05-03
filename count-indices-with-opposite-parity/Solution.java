import java.util.stream.IntStream;

class Solution {
  public int[] countOppositeParity(int[] nums) {
    return IntStream.range(0, nums.length)
        .map(
            i ->
                (int)
                    IntStream.range(i + 1, nums.length)
                        .filter(j -> nums[j] % 2 != nums[i] % 2)
                        .count())
        .toArray();
  }
}