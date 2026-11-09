import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countEvenlyGoodIntegers(long l, long r) {
    return computeEvenlyGoodNum(r) - computeEvenlyGoodNum(l - 1);
  }

  long computeEvenlyGoodNum(long limit) {
    if (limit == 0) {
      return 0;
    }

    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();
    Map<State, Long> dp = Map.of(new State(false, true, true), 1L);
    for (int digit : digits) {
      Map<State, Long> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (int d = 0; d <= (state.strict() ? digit : 9); ++d) {
          boolean hasNonZero = state.hasNonZero() || d != 0;
          boolean strict = state.strict() && d == digit;
          boolean evenlyEven = state.evenlyEven() ^ (hasNonZero && d % 2 == 0);
          State nexState = new State(hasNonZero, strict, evenlyEven);

          nextDp.put(nexState, nextDp.getOrDefault(nexState, 0L) + dp.get(state));
        }
      }

      dp = nextDp;
    }

    return dp.keySet().stream()
        .filter(state -> state.hasNonZero() && state.evenlyEven())
        .mapToLong(dp::get)
        .sum();
  }
}

record State(boolean hasNonZero, boolean strict, boolean evenlyEven) {}
