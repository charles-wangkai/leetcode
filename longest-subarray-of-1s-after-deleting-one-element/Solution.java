import java.util.stream.IntStream;

class Solution {
  public int longestSubarray(int[] nums) {
    int[] leftOneNums = new int[nums.length];
    for (int i = 0; i < leftOneNums.length; ++i) {
      leftOneNums[i] = (i != 0 && nums[i - 1] == 1) ? (leftOneNums[i - 1] + 1) : 0;
    }

    int[] rightOneNums = new int[nums.length];
    for (int i = rightOneNums.length - 1; i >= 0; --i) {
      rightOneNums[i] =
          (i != rightOneNums.length - 1 && nums[i + 1] == 1) ? (rightOneNums[i + 1] + 1) : 0;
    }

    return IntStream.range(0, nums.length)
        .map(i -> leftOneNums[i] + rightOneNums[i])
        .max()
        .getAsInt();
  }
}
