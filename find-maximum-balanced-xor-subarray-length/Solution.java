import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxBalancedSubarray(int[] nums) {
    Map<State, Integer> stateToMinLength = new HashMap<>();
    stateToMinLength.put(new State(0, 0), 0);

    int result = 0;
    int xor = 0;
    int diff = 0;
    for (int i = 0; i < nums.length; ++i) {
      xor ^= nums[i];
      diff += (nums[i] % 2 == 0) ? 1 : -1;
      State state = new State(xor, diff);
      if (stateToMinLength.containsKey(state)) {
        result = Math.max(result, i + 1 - stateToMinLength.get(state));
      } else {
        stateToMinLength.put(state, i + 1);
      }
    }

    return result;
  }
}

record State(int xor, int diff) {}
