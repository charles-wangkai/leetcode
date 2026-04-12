import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int COLUMN_NUM = 6;

  public int minimumDistance(String word) {
    Map<State, Integer> dp = new HashMap<>();
    for (char left = 'A'; left <= 'Z'; ++left) {
      for (char right = 'A'; right <= 'Z'; ++right) {
        dp.put(new State(left, right), 0);
      }
    }

    for (char letter : word.toCharArray()) {
      Map<State, Integer> nextDp = new HashMap<>();
      for (State state : dp.keySet()) {
        for (State nextState :
            new State[] {new State(letter, state.right()), new State(state.left(), letter)}) {
          nextDp.put(
              nextState,
              Math.min(
                  nextDp.getOrDefault(nextState, Integer.MAX_VALUE),
                  dp.get(state) + computeStateDistance(state, nextState)));
        }
      }

      dp = nextDp;
    }

    return dp.values().stream().mapToInt(Integer::intValue).min().getAsInt();
  }

  int computeStateDistance(State state1, State state2) {
    return computeLetterDistance(state1.left(), state2.left())
        + computeLetterDistance(state1.right(), state2.right());
  }

  int toIndex(char letter) {
    return letter - 'A';
  }

  int computeLetterDistance(char letter1, char letter2) {
    return Math.abs(toIndex(letter1) / COLUMN_NUM - toIndex(letter2) / COLUMN_NUM)
        + Math.abs(toIndex(letter1) % COLUMN_NUM - toIndex(letter2) % COLUMN_NUM);
  }
}

record State(char left, char right) {}
