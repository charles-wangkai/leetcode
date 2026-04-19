import java.util.stream.IntStream;

class Solution {
  public int firstStableIndex(int[] nums, int k) {
    return IntStream.range(0, nums.length)
        .filter(
            i ->
                IntStream.rangeClosed(0, i).map(j -> nums[j]).max().getAsInt()
                        - IntStream.range(i, nums.length).map(j -> nums[j]).min().getAsInt()
                    <= k)
        .findFirst()
        .orElse(-1);
  }
}