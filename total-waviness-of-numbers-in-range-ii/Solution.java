import java.util.HashMap;
import java.util.Map;

class Solution {
  public long totalWaviness(long num1, long num2) {
    return computeWavinessNum(num2) - computeWavinessNum(num1 - 1);
  }

  long computeWavinessNum(long limit) {
    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();

    Map<State, Long> stateToWayNum = new HashMap<>();
    Map<State, Long> stateToWaviness = new HashMap<>();
    for (int d = 0; d <= digits[0]; ++d) {
      State state = new State(d, 0, d == digits[0], d != 0);

      stateToWayNum.put(state, 1L);
      stateToWaviness.put(state, 0L);
    }

    for (int i = 1; i < digits.length; ++i) {
      Map<State, Long> nextStateToWayNum = new HashMap<>();
      Map<State, Long> nextStateToWaviness = new HashMap<>();
      for (State state : stateToWaviness.keySet()) {
        for (int d = 0; d <= (state.tight() ? digits[i] : 9); ++d) {
          State nextState =
              new State(
                  d,
                  state.hasNonZero() ? Integer.signum(d - state.lastDigit()) : 0,
                  state.tight() && d == digits[i],
                  state.hasNonZero() || d != 0);

          nextStateToWayNum.put(
              nextState, nextStateToWayNum.getOrDefault(nextState, 0L) + stateToWayNum.get(state));

          nextStateToWaviness.put(
              nextState,
              nextStateToWaviness.getOrDefault(nextState, 0L)
                  + stateToWaviness.get(state)
                  + ((state.prevSignum() != 0
                          && Integer.signum(state.lastDigit() - d) == state.prevSignum())
                      ? stateToWayNum.get(state)
                      : 0));
        }
      }

      stateToWayNum = nextStateToWayNum;
      stateToWaviness = nextStateToWaviness;
    }

    return stateToWaviness.values().stream().mapToLong(Long::longValue).sum();
  }
}

record State(int lastDigit, int prevSignum, boolean tight, boolean hasNonZero) {}
