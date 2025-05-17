class Solution {
  public void sortColors(int[] nums) {
    int index0 = -1;
    int index1 = 0;
    int index2 = nums.length;
    while (index1 < index2) {
      if (nums[index1] == 0) {
        ++index0;
        swap(nums, index1, index0);
        ++index1;
      } else if (nums[index1] == 2) {
        --index2;
        swap(nums, index1, index2);
      } else {
        ++index1;
      }
    }
  }

  void swap(int[] nums, int index1, int index2) {
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }
}
