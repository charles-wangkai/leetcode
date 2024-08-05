import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    NavigableSet<Road> roads = new TreeSet<>(Comparator.comparing(Road::from));
    for (int i = 0; i < n - 1; ++i) {
      roads.add(new Road(i, i + 1));
    }

    int[] result = new int[queries.length];
    for (int i = 0; i < result.length; ++i) {
      Road road = new Road(queries[i][0], queries[i][1]);

      Road floor = roads.floor(road);
      if (floor == null || floor.to() < road.to()) {
        while (true) {
          Road ceiling = roads.ceiling(road);
          if (ceiling == null || ceiling.from() >= road.to()) {
            break;
          }

          roads.remove(ceiling);
        }

        roads.add(road);
      }

      result[i] = roads.size();
    }

    return result;
  }
}

record Road(int from, int to) {}
