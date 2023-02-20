import java.util.Arrays;

class Solution {
  public int searchInsert(int[] nums, int target) {
    int result = Arrays.binarySearch(nums, target);
    if (result < 0) {
      result = -1 - result;
    }

    return result;
  }
}
