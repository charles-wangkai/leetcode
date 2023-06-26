import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minimizeConcatenatedLength(String[] words) {
    Map<State, Integer> stateToMinLength =
        Map.of(
            new State(words[0].charAt(0), words[0].charAt(words[0].length() - 1)),
            words[0].length());
    for (int i = 1; i < words.length; ++i) {
      Map<State, Integer> nextStateToMinLength = new HashMap<>();
      for (State state : stateToMinLength.keySet()) {
        State rightJoined = new State(state.left(), words[i].charAt(words[i].length() - 1));
        nextStateToMinLength.put(
            rightJoined,
            Math.min(
                nextStateToMinLength.getOrDefault(rightJoined, Integer.MAX_VALUE),
                stateToMinLength.get(state)
                    + words[i].length()
                    - ((state.right() == words[i].charAt(0)) ? 1 : 0)));

        State leftJoined = new State(words[i].charAt(0), state.right());
        nextStateToMinLength.put(
            leftJoined,
            Math.min(
                nextStateToMinLength.getOrDefault(leftJoined, Integer.MAX_VALUE),
                words[i].length()
                    + stateToMinLength.get(state)
                    - ((words[i].charAt(words[i].length() - 1) == state.left()) ? 1 : 0)));
      }

      stateToMinLength = nextStateToMinLength;
    }

    return stateToMinLength.values().stream().mapToInt(Integer::intValue).min().getAsInt();
  }
}

record State(char left, char right) {}
