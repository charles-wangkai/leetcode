import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int specialNodes(int n, int[][] edges, int x, int y, int z) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] xDistances = new int[n];
    search(xDistances, adjLists, -1, x, 0);

    int[] yDistances = new int[n];
    search(yDistances, adjLists, -1, y, 0);

    int[] zDistances = new int[n];
    search(zDistances, adjLists, -1, z, 0);

    return (int)
        IntStream.range(0, n)
            .filter(
                i -> {
                  int[] sorted =
                      IntStream.of(xDistances[i], yDistances[i], zDistances[i]).sorted().toArray();

                  return square(sorted[0]) + square(sorted[1]) == square(sorted[2]);
                })
            .count();
  }

  long square(int x) {
    return (long) x * x;
  }

  void search(int[] distances, List<Integer>[] adjLists, int parent, int node, int distance) {
    distances[node] = distance;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(distances, adjLists, node, adj, distance + 1);
      }
    }
  }
 }