import java.util.Arrays;

public class Solution {
  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int remain = target - nums[i];
      int leftIndex = i + 1;
      int rightIndex = nums.length - 1;
      while (leftIndex < rightIndex) {
        if (nums[leftIndex] + nums[rightIndex] < remain) {
          count += rightIndex - leftIndex;
          ++leftIndex;
        } else {
          --rightIndex;
        }
      }
    }
    return count;
  }
}
