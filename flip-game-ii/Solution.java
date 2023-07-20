import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  Map<String, Boolean> state2win = new HashMap<>();

  public boolean canWin(String s) {
    if (state2win.containsKey(s)) {
      return state2win.get(s);
    }

    List<String> states = generatePossibleNextMoves(s);
    boolean win = !states.isEmpty() && states.stream().anyMatch(state -> !canWin(state));
    state2win.put(s, win);

    return win;
  }

  List<String> generatePossibleNextMoves(String s) {
    List<String> states = new ArrayList<>();
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
        states.add(s.substring(0, i) + "--" + s.substring(i + 2));
      }
    }
    return states;
  }
}
