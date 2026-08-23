import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int longestSubarray(int[] nums, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] primeFactorLists =
        Arrays.stream(nums).mapToObj(this::buildPrimeFactors).toArray(List[]::new);

    int result = 0;
    int beginIndex = 0;
    Map<Integer, Integer> primeFactorToCount = new HashMap<>();
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      for (int primeFactor : primeFactorLists[endIndex]) {
        updateMap(primeFactorToCount, primeFactor, 1);
      }

      while (primeFactorToCount.size() > k) {
        for (int primeFactor : primeFactorLists[beginIndex]) {
          updateMap(primeFactorToCount, primeFactor, -1);
        }

        ++beginIndex;
      }

      result = Math.max(result, endIndex - beginIndex + 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> primeFactorToCount, int primeFactor, int delta) {
    primeFactorToCount.put(primeFactor, primeFactorToCount.getOrDefault(primeFactor, 0) + delta);
    primeFactorToCount.remove(primeFactor, 0);
  }

  List<Integer> buildPrimeFactors(int x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        result.add(i);

        while (x % i == 0) {
          x /= i;
        }
      }
    }
    if (x != 1) {
      result.add(x);
    }

    return result;
  }
}