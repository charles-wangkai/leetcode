import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int buildWall(int height, int width, int[] bricks) {
    Set<Integer> brickSet = Arrays.stream(bricks).boxed().collect(Collectors.toSet());
    int[] states =
        IntStream.range(0, 1 << (width - 1))
            .filter(state -> check(width, brickSet, state))
            .toArray();

    Map<Integer, Integer> stateToWayNum = Map.of(0, 1);
    for (int i = 0; i < height; ++i) {
      Map<Integer, Integer> nextStateToWayNum = new HashMap<>();
      for (int state : stateToWayNum.keySet()) {
        for (int nextState : states) {
          if (canNextTo(width, state, nextState)) {
            nextStateToWayNum.put(
                nextState,
                addMod(nextStateToWayNum.getOrDefault(nextState, 0), stateToWayNum.get(state)));
          }
        }
      }

      stateToWayNum = nextStateToWayNum;
    }

    return stateToWayNum.values().stream().reduce(0, this::addMod);
  }

  boolean canNextTo(int width, int state1, int state2) {
    for (int i = 0; i < width - 1; ++i) {
      if ((state1 & (1 << i)) != 0 && (state2 & (1 << i)) != 0) {
        return false;
      }
    }

    return true;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  boolean check(int width, Set<Integer> brickSet, int state) {
    String s = (width == 1) ? "" : Integer.toBinaryString(state);
    while (s.length() != width - 1) {
      s = "0" + s;
    }
    s = "1" + s + "1";

    String s_ = s;
    int[] indices = IntStream.range(0, s.length()).filter(i -> s_.charAt(i) == '1').toArray();

    return IntStream.range(0, indices.length - 1)
        .allMatch(i -> brickSet.contains(indices[i + 1] - indices[i]));
  }
}