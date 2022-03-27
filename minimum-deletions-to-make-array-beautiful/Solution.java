class Solution {
  public int minDeletion(int[] nums) {
    int restCount = 0;
    int prev = -1;
    for (int num : nums) {
      if (prev == -1) {
        prev = num;
      } else if (num != prev) {
        restCount += 2;
        prev = -1;
      }
    }

    return nums.length - restCount;
  }
}