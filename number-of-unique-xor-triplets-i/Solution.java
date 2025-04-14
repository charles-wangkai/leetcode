class Solution {
  public int uniqueXorTriplets(int[] nums) {
    if (nums.length <= 2) {
      return nums.length;
    }

    return Integer.highestOneBit(nums.length) * 2;
  }
}