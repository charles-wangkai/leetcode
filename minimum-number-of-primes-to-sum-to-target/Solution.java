import java.util.Arrays;

class Solution {
  public int minNumberOfPrimes(int n, int m) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    int prime = 1;
    for (int i = 0; i < m; ++i) {
      prime = findNextPrime(prime);

      for (int j = 0; j + prime < dp.length; ++j) {
        if (dp[j] != Integer.MAX_VALUE) {
          dp[j + prime] = Math.min(dp[j + prime], dp[j] + 1);
        }
      }
    }

    return (dp[n] == Integer.MAX_VALUE) ? -1 : dp[n];
  }

  int findNextPrime(int x) {
    while (true) {
      ++x;
      if (isPrime(x)) {
        return x;
      }
    }
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