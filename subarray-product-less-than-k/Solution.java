class Solution {
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int product = 1;
    int endIndex = -1;
    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1 && product * nums[endIndex + 1] < k) {
        ++endIndex;
        product *= nums[endIndex];
      }

      if (endIndex == beginIndex - 1) {
        ++endIndex;
      } else {
        result += endIndex - beginIndex + 1;
        product /= nums[beginIndex];
      }
    }

    return result;
  }
}
