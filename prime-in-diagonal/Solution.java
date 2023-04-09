class Solution {
  public int diagonalPrime(int[][] nums) {
    int result = 0;
    for (int r = 0; r < nums.length; ++r) {
      for (int c = 0; c < nums[r].length; ++c) {
        if ((r == c || r + c == nums.length - 1) && isPrime(nums[r][c])) {
          result = Math.max(result, nums[r][c]);
        }
      }
    }

    return result;
  }

  boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}
