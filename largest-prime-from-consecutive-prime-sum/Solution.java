import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int largestPrime(int n) {
    boolean[] isPrimes = new boolean[n + 1];
    Arrays.fill(isPrimes, true);

    for (int i = 2; i < isPrimes.length; ++i) {
      if (isPrimes[i]) {
        for (int j = i * 2; j < isPrimes.length; j += i) {
          isPrimes[j] = false;
        }
      }
    }

    int[] primes = IntStream.range(2, isPrimes.length).filter(i -> isPrimes[i]).toArray();

    int result = 0;
    int sum = 0;
    for (int prime : primes) {
      sum += prime;
      if (sum > n) {
        break;
      }

      if (isPrimes[sum]) {
        result = sum;
      }
    }

    return result;
  }
}