// See hints

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1_000_000_000;

  static int[] primes;

  static {
    precompute();
  }

  static void precompute() {
    boolean[] composites = new boolean[(int) Math.ceil(Math.sqrt(LIMIT)) + 1];
    for (int i = 2; i < composites.length; ++i) {
      if (!composites[i]) {
        for (int j = i + i; j < composites.length; j += i) {
          composites[j] = true;
        }
      }
    }

    primes = IntStream.range(2, composites.length).filter(i -> !composites[i]).toArray();
  }

  public long minOperations(int[] nums) {
    if (Arrays.stream(nums).allMatch(x -> x == 1)) {
      return 0;
    }

    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    Map<Integer, List<Integer>> valueToDivisors =
        valueToCount.keySet().stream()
            .filter(value -> value != 1)
            .collect(Collectors.toMap(value -> value, this::buildDivisors));

    Map<Integer, Integer> valueToMultipleCount = new HashMap<>();
    for (int value : valueToDivisors.keySet()) {
      for (int divisor : valueToDivisors.get(value)) {
        valueToMultipleCount.put(
            divisor, valueToMultipleCount.getOrDefault(divisor, 0) + valueToCount.get(value));
      }
    }

    int result = nums.length;
    for (int value : valueToDivisors.keySet()) {
      int equal = valueToCount.get(value);
      int multipleCount = valueToMultipleCount.getOrDefault(value, 0);
      int divisorCount =
          valueToDivisors.get(value).stream()
              .mapToInt(divisor -> valueToCount.getOrDefault(divisor, 0))
              .sum();

      result = Math.min(result, 2 * nums.length - 2 * equal - multipleCount - divisorCount);
    }

    return result;
  }

  List<Integer> buildDivisors(int value) {
    List<Factor> factors = new ArrayList<>();
    for (int prime : primes) {
      if (value == 1) {
        break;
      }
      if (prime * prime > value) {
        factors.add(new Factor(value, 1));

        break;
      }

      int exponent = 0;
      while (value % prime == 0) {
        ++exponent;
        value /= prime;
      }

      if (exponent != 0) {
        factors.add(new Factor(prime, exponent));
      }
    }

    List<Integer> divisors = new ArrayList<>();
    search(divisors, factors, 0, 1);

    Collections.sort(divisors);
    divisors.removeLast();

    return divisors;
  }

  void search(List<Integer> divisors, List<Factor> factors, int index, int divisor) {
    if (index == factors.size()) {
      divisors.add(divisor);

      return;
    }

    Factor factor = factors.get(index);

    for (int i = 0; i <= factor.exponent(); ++i) {
      search(divisors, factors, index + 1, divisor);
      divisor *= factor.prime();
    }
  }
}

record Factor(int prime, int exponent) {}
