import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] concatenatedDivisibility(int[] nums, int k) {
    Map<State, int[]> stateToPermutation = Map.of(new State(0, 0), new int[0]);
    for (int i = 0; i < nums.length; ++i) {
      Map<State, int[]> nextStateToPermutation = new HashMap<>();
      for (State state : stateToPermutation.keySet()) {
        for (int index = 0; index < nums.length; ++index) {
          if (((state.mask() >> index) & 1) == 0) {
            State nextState =
                new State(
                    state.mask() + (1 << index),
                    computeNextRemainder(k, state.remainder(), nums[index]));
            int[] nextPermutation =
                IntStream.concat(
                        Arrays.stream(stateToPermutation.get(state)), IntStream.of(nums[index]))
                    .toArray();
            if (!nextStateToPermutation.containsKey(nextState)
                || comparePermutation(nextPermutation, nextStateToPermutation.get(nextState)) < 0) {
              nextStateToPermutation.put(nextState, nextPermutation);
            }
          }
        }
      }

      stateToPermutation = nextStateToPermutation;
    }

    return stateToPermutation.keySet().stream()
        .filter(state -> state.remainder() == 0)
        .map(stateToPermutation::get)
        .min(this::comparePermutation)
        .orElse(new int[0]);
  }

  int comparePermutation(int[] permutation1, int[] permutation2) {
    for (int i = 0; i < permutation1.length; ++i) {
      if (permutation1[i] != permutation2[i]) {
        return Integer.compare(permutation1[i], permutation2[i]);
      }
    }

    return 0;
  }

  int computeNextRemainder(int k, int remainder, int x) {
    for (char c : String.valueOf(x).toCharArray()) {
      remainder = (remainder * 10 + (c - '0')) % k;
    }

    return remainder;
  }
}

record State(int mask, int remainder) {}
