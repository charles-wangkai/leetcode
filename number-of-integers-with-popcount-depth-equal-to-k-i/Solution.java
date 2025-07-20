import java.util.HashMap;
import java.util.Map;

class Solution {
  public long popcountDepth(long n, int k) {
    if (k == 0) {
      return 1;
    }

    Map<State, Long> stateToWayNum = Map.of(new State(0, false), 1L);
    int[] digits = Long.toBinaryString(n).chars().map(c -> c - '0').toArray();
    for (int digit : digits) {
      Map<State, Long> nextStateToWayNum = new HashMap<>();
      for (State state : stateToWayNum.keySet()) {
        for (int d = 0; d <= 1; ++d) {
          if (state.under() || d <= digit) {
            State nextState = new State(state.bitNum() + d, state.under() || d < digit);

            nextStateToWayNum.put(
                nextState,
                nextStateToWayNum.getOrDefault(nextState, 0L) + stateToWayNum.get(state));
          }
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.keySet().stream()
            .filter(state -> state.bitNum() != 0 && computeDepth(state.bitNum()) == k - 1)
            .mapToLong(stateToWayNum::get)
            .sum()
        - ((k == 1) ? 1 : 0);
  }

  int computeDepth(int x) {
    return (x == 1) ? 0 : (1 + computeDepth(Integer.bitCount(x)));
  }
}

record State(int bitNum, boolean under) {}
