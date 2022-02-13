import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumANDSum(int[] nums, int numSlots) {
    Map<Integer, Integer> stateToMaxSum = Map.of(0, 0);
    for (int num : nums) {
      Map<Integer, Integer> nextStateToMaxSum = new HashMap<>();
      for (int state : stateToMaxSum.keySet()) {
        int[] counts = decode(state, numSlots);
        for (int i = 0; i < counts.length; ++i) {
          if (counts[i] != 2) {
            ++counts[i];

            int nextState = encode(counts);
            nextStateToMaxSum.put(
                nextState,
                Math.max(
                    nextStateToMaxSum.getOrDefault(nextState, 0),
                    stateToMaxSum.get(state) + (num & (i + 1))));

            --counts[i];
          }
        }
      }

      stateToMaxSum = nextStateToMaxSum;
    }

    return stateToMaxSum.values().stream().mapToInt(x -> x).max().getAsInt();
  }

  static int[] decode(int state, int size) {
    int[] result = new int[size];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = state % 3;
      state /= 3;
    }

    return result;
  }

  static int encode(int[] counts) {
    int result = 0;
    for (int count : counts) {
      result = result * 3 + count;
    }

    return result;
  }
}