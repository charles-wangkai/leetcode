import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final String MOVES = "WEF";

  public int countWinningSequences(String s) {
    Map<State, Integer> stateToWayNum = Map.of(new State((char) 0, 0), 1);
    for (char move : s.toCharArray()) {
      Map<State, Integer> nextStateToWayNum = new HashMap<>();
      for (State state : stateToWayNum.keySet()) {
        for (char m : MOVES.toCharArray()) {
          if (m != state.lastMove()) {
            State nextState = new State(m, state.scoreDiff() + computeScore(m, move));
            nextStateToWayNum.put(
                nextState,
                addMod(nextStateToWayNum.getOrDefault(nextState, 0), stateToWayNum.get(state)));
          }
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.keySet().stream()
        .filter(state -> state.scoreDiff() > 0)
        .mapToInt(stateToWayNum::get)
        .reduce(this::addMod)
        .getAsInt();
  }

  int computeScore(char move1, char move2) {
    int indexDiff = Math.floorMod(MOVES.indexOf(move1) - MOVES.indexOf(move2), 3);

    return (indexDiff == 2) ? -1 : indexDiff;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record State(char lastMove, int scoreDiff) {}
