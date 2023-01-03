class Solution {
  public long evenProduct(int[] nums) {
    long oddNum = 0;
    int length = 0;
    for (int i = 0; i <= nums.length; ++i) {
      if (i != nums.length && nums[i] % 2 != 0) {
        ++length;
      } else {
        oddNum += length * (length + 1L) / 2;
        length = 0;
      }
    }

    return nums.length * (nums.length + 1L) / 2 - oddNum;
  }
}
