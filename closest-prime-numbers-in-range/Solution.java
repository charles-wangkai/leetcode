import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] closestPrimes(int left, int right) {
    int[] primes = buildPrimes(left, right);

    return IntStream.range(0, primes.length - 1)
        .boxed()
        .min(
            Comparator.<Integer, Integer>comparing(i -> primes[i + 1] - primes[i])
                .thenComparing(i -> i))
        .map(i -> new int[] {primes[i], primes[i + 1]})
        .orElse(new int[] {-1, -1});
  }

  int[] buildPrimes(int left, int right) {
    boolean[] primes = new boolean[right + 1];
    Arrays.fill(primes, true);
    primes[1] = false;

    for (int i = 1; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i + i; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return IntStream.range(left, primes.length).filter(i -> primes[i]).toArray();
  }
}
