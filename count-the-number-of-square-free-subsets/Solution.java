import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

  public int squareFreeSubsets(int[] nums) {
    int[] dp = new int[1 << PRIMES.length];
    dp[0] = 1;
    for (int num : nums) {
      int state = toState(num);
      if (state != -1) {
        for (int i = dp.length - 1; i >= 0; --i) {
          if ((i & state) == state) {
            dp[i] = addMod(dp[i], dp[i - state]);
          }
        }
      }
    }

    return addMod(Arrays.stream(dp).reduce(this::addMod).getAsInt(), -1);
  }

  int toState(int x) {
    int result = 0;
    for (int i = 0; i < PRIMES.length; ++i) {
      if (x % PRIMES[i] == 0) {
        if (x / PRIMES[i] % PRIMES[i] == 0) {
          return -1;
        }

        result += 1 << i;
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
