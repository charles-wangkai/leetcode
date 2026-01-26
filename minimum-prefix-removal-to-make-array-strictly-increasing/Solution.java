class Solution {
  public int minimumPrefixLength(int[] nums) {
    int index = nums.length - 1;
    while (index != 0 && nums[index - 1] < nums[index]) {
      --index;
    }

    return index;
  }
}