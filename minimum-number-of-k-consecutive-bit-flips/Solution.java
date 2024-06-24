class Solution {
  public int minKBitFlips(int[] nums, int k) {
    int result = 0;
    boolean[] flipped = new boolean[nums.length];
    boolean currentFlipped = false;
    for (int i = 0; i < nums.length; ++i) {
      if (i >= k && flipped[i - k]) {
        currentFlipped ^= true;
      }

      if ((nums[i] == 1) == currentFlipped) {
        if (i + k > nums.length) {
          return -1;
        }

        flipped[i] = true;
        currentFlipped ^= true;
        ++result;
      }
    }

    return result;
  }
}
