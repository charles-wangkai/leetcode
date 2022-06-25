import java.util.stream.IntStream;

class Solution {
  public boolean checkPossibility(int[] nums) {
    int leftIndex = 0;
    while (leftIndex + 1 != nums.length && nums[leftIndex] <= nums[leftIndex + 1]) {
      ++leftIndex;
    }

    int rightIndex = nums.length - 1;
    while (rightIndex != 0 && nums[rightIndex - 1] <= nums[rightIndex]) {
      --rightIndex;
    }

    return IntStream.rangeClosed(rightIndex - 1, leftIndex + 1)
        .anyMatch(i -> get(nums, i - 1) <= get(nums, i + 1));
  }

  int get(int[] nums, int index) {
    if (index < 0) {
      return Integer.MIN_VALUE;
    }
    if (index >= nums.length) {
      return Integer.MAX_VALUE;
    }

    return nums[index];
  }
}
