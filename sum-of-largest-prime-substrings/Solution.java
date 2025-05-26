import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long sumOfLargestPrimes(String s) {
    return IntStream.range(0, s.length())
        .boxed()
        .flatMapToLong(
            i ->
                IntStream.range(i, s.length())
                    .mapToLong(j -> Long.parseLong(s.substring(i, j + 1))))
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToLong(Long::longValue)
        .distinct()
        .filter(this::isPrime)
        .limit(3)
        .sum();
  }

  boolean isPrime(long x) {
    if (x < 2) {
      return false;
    }

    for (int i = 2; (long) i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}