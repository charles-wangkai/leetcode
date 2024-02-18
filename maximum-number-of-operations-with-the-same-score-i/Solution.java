class Solution {
  public int maxOperations(int[] nums) {
    int result = 1;
    for (int i = 2; i + 1 < nums.length && nums[i] + nums[i + 1] == nums[0] + nums[1]; i += 2) {
      ++result;
    }

    return result;
  }
}