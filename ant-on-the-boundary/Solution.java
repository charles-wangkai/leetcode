class Solution {
  public int returnToBoundaryCount(int[] nums) {
    int result = 0;
    int pos = 0;
    for (int num : nums) {
      pos += num;
      if (pos == 0) {
        ++result;
      }
    }

    return result;
  }
}