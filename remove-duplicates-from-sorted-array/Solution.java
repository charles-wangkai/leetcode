class Solution {
  public int removeDuplicates(int[] nums) {
    int length = 0;
    for (int num : nums) {
      if (length == 0 || num != nums[length - 1]) {
        nums[length] = num;
        ++length;
      }
    }

    return length;
  }
}
