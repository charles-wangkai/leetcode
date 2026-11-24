import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] nums) {
    int[] leftIndices = new int[nums.length];
    for (int i = 0; i < leftIndices.length; ++i) {
      leftIndices[i] = (i != 0 && nums[i] == nums[i - 1]) ? leftIndices[i - 1] : i;
    }

    int[] rightIndices = new int[nums.length];
    for (int i = rightIndices.length - 1; i >= 0; --i) {
      rightIndices[i] =
          (i != rightIndices.length - 1 && nums[i] == nums[i + 1]) ? rightIndices[i + 1] : i;
    }

    return IntStream.range(1, nums.length - 1)
        .mapToLong(i -> nums[i] * (nums.length - (rightIndices[i] - leftIndices[i] + 1L)))
        .min()
        .getAsLong();
  }
}