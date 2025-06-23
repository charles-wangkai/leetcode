import java.util.Arrays;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Solution {
  public int primeSubarray(int[] nums, int k) {
    Set<Integer> primes =
        Arrays.stream(nums).filter(this::isPrime).boxed().collect(Collectors.toSet());

    return computeBalancedNum(nums, k, primes) - computeLessTwoPrimeNum(nums, primes);
  }

  int computeBalancedNum(int[] nums, int k, Set<Integer> primes) {
    int result = 0;
    SortedMap<Integer, Integer> primeToCount = new TreeMap<>();
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      if (primes.contains(nums[endIndex])) {
        updateMap(primeToCount, nums[endIndex], 1);
      }

      while (!primeToCount.isEmpty() && primeToCount.lastKey() - primeToCount.firstKey() > k) {
        if (primes.contains(nums[beginIndex])) {
          updateMap(primeToCount, nums[beginIndex], -1);
        }

        ++beginIndex;
      }

      result += endIndex - beginIndex + 1;
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> primeToCount, int prime, int delta) {
    primeToCount.put(prime, primeToCount.getOrDefault(prime, 0) + delta);
    primeToCount.remove(prime, 0);
  }

  int computeLessTwoPrimeNum(int[] nums, Set<Integer> primes) {
    int result = 0;
    int primeCount = 0;
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      if (primes.contains(nums[endIndex])) {
        ++primeCount;
      }

      while (primeCount == 2) {
        if (primes.contains(nums[beginIndex])) {
          --primeCount;
        }

        ++beginIndex;
      }

      result += endIndex - beginIndex + 1;
    }

    return result;
  }

  boolean isPrime(int x) {
    if (x < 2) {
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