// https://leetcode.com/problems/count-beautiful-numbers/solutions/6541308/straight-forward-digit-dp-leverage-logic-of-prod-of-num-2-a-3-b-5-c-7-d-prime-factorization/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int beautifulNumbers(int l, int r) {
    return computeWayNum(r) - computeWayNum(l - 1);
  }

  int computeWayNum(int limit) {
    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();

    Map<State, Integer> stateToWayNum = Map.of(new State(true, 1, 0), 1);
    for (int digit : digits) {
      Map<State, Integer> nextStateToWayNum = new HashMap<>();
      for (State state : stateToWayNum.keySet()) {
        for (int d = 0; d <= (state.tight() ? digit : 9); ++d) {
          State nextState =
              new State(
                  state.tight() && d == digit,
                  (state.sum() + d == 0) ? 1 : (state.product() * d),
                  state.sum() + d);
          nextStateToWayNum.put(
              nextState, nextStateToWayNum.getOrDefault(nextState, 0) + stateToWayNum.get(state));
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.keySet().stream()
        .filter(state -> state.sum() != 0 && state.product() % state.sum() == 0)
        .mapToInt(stateToWayNum::get)
        .sum();
  }
}

record State(boolean tight, int product, int sum) {}
