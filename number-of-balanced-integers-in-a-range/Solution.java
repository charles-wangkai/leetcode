import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int DIGIT_NUM = 16;

  public long countBalanced(long low, long high) {
    return computeWayNum(high) - computeWayNum(low - 1);
  }

  static long computeWayNum(long limit) {
    int[] digits = toDigits(limit);

    Map<State, Long> dp = Map.of(new State(0, true), 1L);
    for (int i = 0; i < digits.length; ++i) {
      Map<State, Long> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (int d = 0; d <= (state.strict() ? digits[i] : 9); ++d) {
          State nextState =
              new State(
                  state.delta() + ((i % 2 == 0) ? 1 : -1) * d, state.strict() && d == digits[i]);
          nextDp.put(nextState, nextDp.getOrDefault(nextState, 0L) + dp.get(state));
        }
      }

      dp = nextDp;
    }

    return dp.keySet().stream().filter(state -> state.delta() == 0).mapToLong(dp::get).sum();
  }

  static int[] toDigits(long x) {
    String s = String.valueOf(x);

    int[] result = new int[DIGIT_NUM];
    for (int i = 0; i < s.length(); ++i) {
      result[result.length - 1 - i] = s.charAt(s.length() - 1 - i) - '0';
    }

    return result;
  }
}

record State(int delta, boolean strict) {}
