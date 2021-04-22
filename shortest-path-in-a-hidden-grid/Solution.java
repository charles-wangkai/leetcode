import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

// This is the GridMaster's API interface.
// You should not implement it, or speculate about its implementation
interface GridMaster {
  boolean canMove(char direction);

  void move(char direction);

  boolean isTarget();
}

class Solution {
  static final char[] DIRECTIONS = {'U', 'R', 'D', 'L'};
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int findShortestPath(GridMaster master) {
    Set<Point> availableCells = new HashSet<>();
    Point targetCell = search(master, availableCells, 0, 0);
    if (targetCell == null) {
      return -1;
    }

    Map<Point, Integer> cellToDistance = new HashMap<>();
    cellToDistance.put(new Point(0, 0), 0);
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < DIRECTIONS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        Point adj = new Point(adjR, adjC);
        if (availableCells.contains(adj) && !cellToDistance.containsKey(adj)) {
          cellToDistance.put(adj, cellToDistance.get(head) + 1);
          queue.offer(adj);
        }
      }
    }

    return cellToDistance.get(targetCell);
  }

  Point search(GridMaster master, Set<Point> availableCells, int r, int c) {
    availableCells.add(new Point(r, c));

    Point result = null;
    for (int i = 0; i < DIRECTIONS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (master.canMove(DIRECTIONS[i]) && !availableCells.contains(new Point(adjR, adjC))) {
        master.move(DIRECTIONS[i]);

        Point subResult = search(master, availableCells, adjR, adjC);
        if (subResult != null) {
          result = subResult;
        }

        master.move(DIRECTIONS[(i + 2) % DIRECTIONS.length]);
      }
    }

    if (result == null) {
      result = master.isTarget() ? new Point(r, c) : null;
    }

    return result;
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, c);
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;

    return r == other.r && c == other.c;
  }
}
