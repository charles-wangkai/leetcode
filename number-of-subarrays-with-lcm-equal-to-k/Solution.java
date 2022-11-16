class Solution {
  public int subarrayLCM(int[] nums, int k) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      int lcm = -1;
      for (int j = i; j < nums.length; ++j) {
        lcm = (lcm == -1) ? nums[j] : computeLcm(lcm, nums[j]);
        if (lcm > k) {
          break;
        }
        if (lcm == k) {
          ++result;
        }
      }
    }

    return result;
  }

  int computeLcm(int x, int y) {
    return x * y / gcd(x, y);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
