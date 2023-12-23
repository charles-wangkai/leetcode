import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean isPathCrossing(String path) {
    int x = 0;
    int y = 0;
    Set<Point> seen = new HashSet<>();
    seen.add(new Point(x, y));

    for (char command : path.toCharArray()) {
      if (command == 'N') {
        ++y;
      } else if (command == 'S') {
        --y;
      } else if (command == 'E') {
        ++x;
      } else {
        --x;
      }

      Point point = new Point(x, y);
      if (seen.contains(point)) {
        return true;
      }
      seen.add(point);
    }

    return false;
  }
}

record Point(int x, int y) {}
