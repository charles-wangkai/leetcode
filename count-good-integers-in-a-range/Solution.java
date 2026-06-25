import java.util.HashMap;
import java.util.Map;

class Solution {
  public long goodIntegers(long l, long r, int k) {
    return computeGoodNum(k, r) - computeGoodNum(k, l - 1);
  }

  long computeGoodNum(int k, long limit) {
    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();

    Map<State, Long> dp = Map.of(new State(null, true), 1L);
    for (int digit : digits) {
      Map<State, Long> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (int d = 0; d <= 9; ++d) {
          if ((state.lastDigit() == null && (!state.strict() || d <= digit))
              || (state.lastDigit() != null
                  && (!state.strict() || d <= digit)
                  && Math.abs(d - state.lastDigit()) <= k)) {
            State nextState =
                new State(
                    (state.lastDigit() == null && d == 0) ? null : d, state.strict() && d == digit);
            nextDp.put(nextState, nextDp.getOrDefault(nextState, 0L) + dp.get(state));
          }
        }
      }

      dp = nextDp;
    }

    return dp.values().stream().mapToLong(Long::longValue).sum();
  }
}

record State(Integer lastDigit, boolean strict) {}
