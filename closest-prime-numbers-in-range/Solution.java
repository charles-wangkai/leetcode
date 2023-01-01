import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] closestPrimes(int left, int right) {
    int[] primes = buildPrimes(right);

    return IntStream.range(0, primes.length - 1)
        .filter(i -> primes[i] >= left && primes[i + 1] <= right)
        .boxed()
        .min(Comparator.comparing((Integer i) -> primes[i + 1] - primes[i]).thenComparing(i -> i))
        .map(i -> new int[] {primes[i], primes[i + 1]})
        .orElse(new int[] {-1, -1});
  }

  int[] buildPrimes(int limit) {
    boolean[] primes = new boolean[limit + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i + i; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return IntStream.range(2, primes.length).filter(i -> primes[i]).toArray();
  }
}
