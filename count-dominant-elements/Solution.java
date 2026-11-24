import java.util.stream.IntStream;

class Solution {
  public int countDominantElements(int[] nums) {
    return (int)
        IntStream.range(0, nums.length)
            .filter(i -> {
              if (i == 0 && i == nums.length - 1) {
                return true;
              }
              if (i == 0) {
                return nums[i] >= nums[i + 1];
              }
              if (i == nums.length - 1) {
                return nums[i] >= nums[i - 1];
              }

              return 2 * nums[i] >= nums[i - 1] + nums[i + 1];
            })
            .count();
  }
}