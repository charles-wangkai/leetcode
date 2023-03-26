import java.util.Arrays;

class Solution {
  public boolean primeSubOperation(int[] nums) {
    boolean[] primes = new boolean[Arrays.stream(nums).max().getAsInt() + 1];
    for (int i = 2; i < primes.length; ++i) {
      primes[i] = isPrime(i);
    }

    for (int i = 0; i < nums.length; ++i) {
      for (int j = nums[i] - 1 - ((i == 0) ? 0 : nums[i - 1]); j >= 0; --j) {
        if (primes[j]) {
          nums[i] -= j;

          break;
        }
      }

      if (i != 0 && nums[i] <= nums[i - 1]) {
        return false;
      }
    }

    return true;
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}
