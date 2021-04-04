import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxHappyGroups(int batchSize, int[] groups) {
    int[] counts = new int[batchSize];
    for (int group : groups) {
      ++counts[group % batchSize];
    }
    int happyNum = counts[0];
    counts[0] = 0;

    Map<State, Integer> stateToMaxHappyNum = Map.of(new State(counts, 0), happyNum);
    for (int i = 0; i < groups.length - happyNum; ++i) {
      Map<State, Integer> nextStateToMaxHappyNum = new HashMap<>();
      for (State state : stateToMaxHappyNum.keySet()) {
        for (int j = 0; j < state.counts.length; ++j) {
          if (state.counts[j] != 0) {
            int[] nextCounts = state.counts.clone();
            --nextCounts[j];

            int nextRemainder = (state.remainder + j) % batchSize;

            State nextState = new State(nextCounts, nextRemainder);
            nextStateToMaxHappyNum.put(
                nextState,
                Math.max(
                    nextStateToMaxHappyNum.getOrDefault(nextState, 0),
                    stateToMaxHappyNum.get(state) + ((state.remainder == 0) ? 1 : 0)));
          }
        }
      }

      stateToMaxHappyNum = nextStateToMaxHappyNum;
    }

    return stateToMaxHappyNum.values().stream().mapToInt(x -> x).max().getAsInt();
  }
}

class State {
  int[] counts;
  int remainder;

  State(int[] counts, int remainder) {
    this.counts = counts;
    this.remainder = remainder;
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(new Object[] {counts, remainder});
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return Arrays.equals(counts, other.counts) && remainder == other.remainder;
  }
}
