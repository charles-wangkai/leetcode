import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int LIMIT = 100_000_000;

  public int maxPartitionFactor(int[][] points) {
    if (points.length == 2) {
      return 0;
    }

    int result = -1;
    int lower = 0;
    int upper = 4 * LIMIT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(points, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[][] points, int partitionFactor) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjList = new List[points.length];
    for (int i = 0; i < adjList.length; ++i) {
      adjList[i] = new ArrayList<>();
    }
    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        if (Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])
            < partitionFactor) {
          adjList[i].add(j);
          adjList[j].add(i);
        }
      }
    }

    int[] colors = new int[points.length];
    Arrays.fill(colors, -1);
    for (int i = 0; i < colors.length; ++i) {
      if (colors[i] == -1 && !fill(colors, adjList, i, 0)) {
        return false;
      }
    }

    return true;
  }

  boolean fill(int[] colors, List<Integer>[] adjList, int node, int color) {
    if (colors[node] != -1) {
      return colors[node] == color;
    }

    colors[node] = color;

    for (int adj : adjList[node]) {
      if (!fill(colors, adjList, adj, 1 - color)) {
        return false;
      }
    }

    return true;
  }
}