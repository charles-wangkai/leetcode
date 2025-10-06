import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countNoZeroPairs(long n) {
    return search(new HashMap<>(), n, false);
  }

  long search(Map<State, Long> cache, long sum, boolean canOnlyOneSide) {
    if (sum == 0) {
      return 1;
    }

    State state = new State(sum, canOnlyOneSide);
    if (!cache.containsKey(state)) {
      long result = (canOnlyOneSide && String.valueOf(sum).chars().allMatch(c -> c != '0')) ? 2 : 0;

      for (int d1 = 1; d1 <= 9 && d1 < sum % 10; ++d1) {
        result += search(cache, sum / 10, true);
      }

      if (sum >= 10) {
        for (int d1 = 1; d1 <= 9; ++d1) {
          int d2 = 10 + (int) (sum % 10) - d1;
          if (d2 >= 1 && d2 <= 9) {
            result += search(cache, sum / 10 - 1, true);
          }
        }
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

record State(long sum, boolean canOnlyOneSide) {}
