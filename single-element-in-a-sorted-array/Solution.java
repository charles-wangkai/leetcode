class Solution {
  public int singleNonDuplicate(int[] nums) {
    int result = -1;
    int lower = 0;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if ((middle % 2 == 0 && middle + 1 != nums.length && nums[middle] == nums[middle + 1])
          || (middle % 2 != 0 && nums[middle] == nums[middle - 1])) {
        lower = middle + 1;
      } else {
        result = nums[middle];
        upper = middle - 1;
      }
    }

    return result;
  }
}
