import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxScore(int[] nums, int maxVal) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    Map<Integer, Integer> factorToCount = new HashMap<>();
    for (int value : nums) {
      List<Integer> primes = buildPrimes(value);

      for (int mask = 1; mask < 1 << primes.size(); ++mask) {
        int factor = computeFactor(primes, mask);

        factorToCount.put(factor, factorToCount.getOrDefault(factor, 0) + 1);
      }
    }

    return IntStream.concat(Arrays.stream(nums), IntStream.rangeClosed(1, maxVal))
        .distinct()
        .map(
            selectedValue -> {
              List<Integer> primes = buildPrimes(selectedValue);

              int nonCoPrimeNum = 0;
              for (int mask = 1; mask < 1 << primes.size(); ++mask) {
                int factor = computeFactor(primes, mask);

                nonCoPrimeNum +=
                    ((Integer.bitCount(mask) % 2 == 0) ? -1 : 1)
                        * factorToCount.getOrDefault(factor, 0);
              }

              int modificationCost;
              if (set.contains(selectedValue)) {
                modificationCost = Math.max(0, nonCoPrimeNum - 1);
              } else {
                modificationCost = Math.max(1, nonCoPrimeNum);
              }

              return selectedValue - modificationCost;
            })
        .max()
        .getAsInt();
  }

  List<Integer> buildPrimes(int value) {
    List<Integer> result = new ArrayList<>();
    for (int i = 2; i * i <= value; ++i) {
      if (value % i == 0) {
        result.add(i);

        while (value % i == 0) {
          value /= i;
        }
      }
    }
    if (value != 1) {
      result.add(value);
    }

    return result;
  }

  int computeFactor(List<Integer> primes, int mask) {
    int result = 1;
    for (int i = 0; i < primes.size(); ++i) {
      if (((mask >> i) & 1) == 1) {
        result *= primes.get(i);
      }
    }

    return result;
  }
}