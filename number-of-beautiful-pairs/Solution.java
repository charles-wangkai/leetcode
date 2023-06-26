class Solution {
  public int countBeautifulPairs(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (gcd(String.valueOf(nums[i]).charAt(0) - '0', nums[j] % 10) == 1) {
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
