import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

// This is the GridMaster's API interface.
// You should not implement it, or speculate about its implementation
interface GridMaster {
  boolean canMove(char direction);

  int move(char direction);

  boolean isTarget();
}

class Solution {
  static final char[] DIRECTIONS = {'U', 'R', 'D', 'L'};
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int findShortestPath(GridMaster master) {
    Map<Point, Integer> cellToCost = new HashMap<>();
    Point targetCell = search(master, cellToCost, 0, 0);
    if (targetCell == null) {
      return -1;
    }

    Map<Point, Integer> cellToDistance = new HashMap<>();
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(new Point(0, 0), 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (!cellToDistance.containsKey(head.point)) {
        cellToDistance.put(head.point, head.distance);

        for (int i = 0; i < DIRECTIONS.length; ++i) {
          int adjR = head.point.r + R_OFFSETS[i];
          int adjC = head.point.c + C_OFFSETS[i];
          Point adj = new Point(adjR, adjC);
          if (cellToCost.containsKey(adj) && !cellToDistance.containsKey(adj)) {
            pq.offer(new Element(adj, cellToDistance.get(head.point) + cellToCost.get(adj)));
          }
        }
      }
    }

    return cellToDistance.get(targetCell);
  }

  Point search(GridMaster master, Map<Point, Integer> cellToCost, int r, int c) {
    Point result = null;
    for (int i = 0; i < DIRECTIONS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (master.canMove(DIRECTIONS[i]) && !cellToCost.containsKey(new Point(adjR, adjC))) {
        cellToCost.put(new Point(adjR, adjC), master.move(DIRECTIONS[i]));

        Point subResult = search(master, cellToCost, adjR, adjC);
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

class Element {
  Point point;
  int distance;

  Element(Point point, int distance) {
    this.point = point;
    this.distance = distance;
  }
}
