import java.util.stream.IntStream;

class Solution {
  public int sumOfBeauties(int[] nums) {
    int[] leftMaxs = new int[nums.length];
    leftMaxs[0] = Integer.MIN_VALUE;
    for (int i = 1; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max(leftMaxs[i - 1], nums[i - 1]);
    }

    int[] rightMins = new int[nums.length];
    rightMins[rightMins.length - 1] = Integer.MAX_VALUE;
    for (int i = rightMins.length - 2; i >= 0; --i) {
      rightMins[i] = Math.min(rightMins[i + 1], nums[i + 1]);
    }

    return IntStream.rangeClosed(1, nums.length - 2)
        .map(
            i -> {
              if (nums[i] > leftMaxs[i] && nums[i] < rightMins[i]) {
                return 2;
              } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                return 1;
              }

              return 0;
            })
        .sum();
  }
}
