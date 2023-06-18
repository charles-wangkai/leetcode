import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int specialPerm(int[] nums) {
    Map<State, Integer> stateToWayNum =
        IntStream.range(0, nums.length)
            .boxed()
            .collect(Collectors.toMap(i -> new State(1 << i, nums[i]), i -> 1));
    for (int i = 0; i < nums.length - 1; ++i) {
      Map<State, Integer> nextStateToWayNum = new HashMap<>();
      for (State state : stateToWayNum.keySet()) {
        for (int j = 0; j < nums.length; ++j) {
          if (((state.mask() >> j) & 1) == 0
              && (state.last() % nums[j] == 0 || nums[j] % state.last() == 0)) {
            State nextState = new State(state.mask() | (1 << j), nums[j]);
            nextStateToWayNum.put(
                nextState,
                addMod(nextStateToWayNum.getOrDefault(nextState, 0), stateToWayNum.get(state)));
          }
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.values().stream().reduce(this::addMod).orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record State(int mask, int last) {}
