import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countPartitions(int[] nums, int k) {
    Map<State, Integer> stateToCount = Map.of(new State(0, 0), 1);
    for (int num : nums) {
      Map<State, Integer> nextStateToCount = new HashMap<>();
      for (State state : stateToCount.keySet()) {
        for (State nextState :
            new State[] {
              new State(Math.min(k, state.sum1() + num), state.sum2()),
              new State(state.sum1(), Math.min(k, state.sum2() + num))
            }) {
          nextStateToCount.put(
              nextState,
              addMod(nextStateToCount.getOrDefault(nextState, 0), stateToCount.get(state)));
        }
      }

      stateToCount = nextStateToCount;
    }

    return stateToCount.getOrDefault(new State(k, k), 0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record State(int sum1, int sum2) {}
