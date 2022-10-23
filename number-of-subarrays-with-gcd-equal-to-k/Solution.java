class Solution {
  public int subarrayGCD(int[] nums, int k) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      int g = -1;
      for (int j = i; j < nums.length; ++j) {
        g = (j == i) ? nums[j] : gcd(g, nums[j]);
        if (g == k) {
          ++result;
        }
      }
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
