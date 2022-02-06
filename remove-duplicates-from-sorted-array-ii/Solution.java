class Solution {
  public int removeDuplicates(int[] nums) {
    int length = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (length <= 1 || nums[i] != nums[length - 2]) {
        nums[length] = nums[i];
        ++length;
      }
    }

    return length;
  }
}
