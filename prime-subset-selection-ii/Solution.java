import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public List<Integer> maxPrimeSubset(int n, int s) {
    int[] primes = IntStream.rangeClosed(2, n).filter(this::isPrime).toArray();

    @SuppressWarnings("unchecked")
    List<Integer>[] dp = new List[s + 1];
    dp[0] = List.of();

    for (int prime : primes) {
      for (int i = dp.length - 1; i >= prime; --i) {
        if (dp[i - prime] != null) {
          dp[i] = merge(dp[i], Stream.concat(dp[i - prime].stream(), Stream.of(prime)).toList());
        }
      }

      if (prime < dp.length) {
        dp[prime] = List.of(prime);
      }
    }

    for (int i = dp.length - 1; ; --i) {
      if (dp[i] != null) {
        return dp[i];
      }
    }
  }

  List<Integer> merge(List<Integer> subset1, List<Integer> subset2) {
    return (subset1 != null
            && (subset1.size() < subset2.size()
                || (subset1.size() == subset2.size() && isSmaller(subset1, subset2))))
        ? subset1
        : subset2;
  }

  boolean isSmaller(List<Integer> subset1, List<Integer> subset2) {
    for (int i = 0; ; ++i) {
      if (!subset1.get(i).equals(subset2.get(i))) {
        return subset1.get(i) < subset2.get(i);
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