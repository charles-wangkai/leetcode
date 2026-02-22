import static java.util.Map.entry;

import java.util.HashMap;
import java.util.Map;

class Solution {
  static final Map<Integer, State> VALUE_TO_STATE =
      Map.ofEntries(
          entry(1, new State(0, 0, 0)),
          entry(2, new State(1, 0, 0)),
          entry(3, new State(0, 1, 0)),
          entry(4, new State(2, 0, 0)),
          entry(5, new State(0, 0, 1)),
          entry(6, new State(1, 1, 0)));

  public int countSequences(int[] nums, long k) {
    State target = toState(k);
    if (target == null) {
      return 0;
    }

    Map<State, Integer> dp = Map.of(new State(0, 0, 0), 1);
    for (int num : nums) {
      Map<State, Integer> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (int factor = -1; factor <= 1; ++factor) {
          State nextState =
              new State(
                  state.exp2() + factor * VALUE_TO_STATE.get(num).exp2(),
                  state.exp3() + factor * VALUE_TO_STATE.get(num).exp3(),
                  state.exp5() + factor * VALUE_TO_STATE.get(num).exp5());

          nextDp.put(nextState, nextDp.getOrDefault(nextState, 0) + dp.get(state));
        }
      }

      dp = nextDp;
    }

    return dp.getOrDefault(target, 0);
  }

  State toState(long x) {
    int exp2 = 0;
    while (x % 2 == 0) {
      ++exp2;
      x /= 2;
    }

    int exp3 = 0;
    while (x % 3 == 0) {
      ++exp3;
      x /= 3;
    }

    int exp5 = 0;
    while (x % 5 == 0) {
      ++exp5;
      x /= 5;
    }

    return (x == 1) ? new State(exp2, exp3, exp5) : null;
  }
}

record State(int exp2, int exp3, int exp5) {}
