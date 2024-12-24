import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
    return Math.max(
        Math.max(computeDiameter(edges1), computeDiameter(edges2)),
        computeMinDistance(edges1) + computeMinDistance(edges2) + 1);
  }

  int computeDiameter(int[][] edges) {
    int[] distances = computeDistances(edges, 0);
    int root =
        IntStream.range(0, distances.length)
            .boxed()
            .max(Comparator.comparing(i -> distances[i]))
            .get();

    return Arrays.stream(computeDistances(edges, root)).max().getAsInt();
  }

  int[] computeDistances(int[][] edges, int root) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[edges.length + 1];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] distances = new int[edges.length + 1];
    search(distances, adjLists, 0, -1, root);

    return distances;
  }

  void search(int[] distances, List<Integer>[] adjLists, int distance, int parent, int node) {
    distances[node] = distance;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(distances, adjLists, distance + 1, node, adj);
      }
    }
  }

  int computeMinDistance(int[][] edges) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[edges.length + 1];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    int result = 0;
    int[] leaves = IntStream.range(0, adjSets.length).filter(i -> adjSets[i].size() == 1).toArray();
    while (leaves.length != 0) {
      Set<Integer> impacted = new HashSet<>();
      for (int leaf : leaves) {
        for (int adj : adjSets[leaf]) {
          adjSets[adj].remove(leaf);
          impacted.add(adj);
        }
      }

      ++result;
      leaves =
          impacted.stream()
              .filter(i -> adjSets[i].size() == 1)
              .mapToInt(Integer::intValue)
              .toArray();
    }

    return result;
  }
}