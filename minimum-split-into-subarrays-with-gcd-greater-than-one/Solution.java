class Solution {
  public int minimumSplits(int[] nums) {
    int result = 0;
    int beginIndex = 0;
    while (beginIndex != nums.length) {
      int g = nums[beginIndex];
      int endIndex = beginIndex;
      while (endIndex != nums.length - 1) {
        int nextG = gcd(g, nums[endIndex + 1]);
        if (nextG == 1) {
          break;
        }

        g = nextG;
        ++endIndex;
      }

      ++result;
      beginIndex = endIndex + 1;
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
