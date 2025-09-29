import static java.util.Map.entry;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  static final Map<Character, Offset> MOVE_TO_OFFSET =
      Map.ofEntries(
          entry('U', new Offset(0, 1)),
          entry('D', new Offset(0, -1)),
          entry('L', new Offset(-1, 0)),
          entry('R', new Offset(1, 0)));

  public int distinctPoints(String s, int k) {
    Set<Offset> seen = new HashSet<>();
    int x = 0;
    int y = 0;
    for (int i = 0; i < s.length(); ++i) {
      x += MOVE_TO_OFFSET.get(s.charAt(i)).dx();
      y += MOVE_TO_OFFSET.get(s.charAt(i)).dy();

      if (i >= k - 1) {
        seen.add(new Offset(x, y));

        x -= MOVE_TO_OFFSET.get(s.charAt(i - k + 1)).dx();
        y -= MOVE_TO_OFFSET.get(s.charAt(i - k + 1)).dy();
      }
    }

    return seen.size();
  }
}

record Offset(int dx, int dy) {}
