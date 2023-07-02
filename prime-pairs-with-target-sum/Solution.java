import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> findPrimePairs(int n) {
    boolean[] primes = new boolean[n];
    Arrays.fill(primes, true);
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i + i; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return IntStream.rangeClosed(2, n / 2)
        .filter(i -> primes[i] && primes[n - i])
        .mapToObj(i -> List.of(i, n - i))
        .toList();
  }
}
