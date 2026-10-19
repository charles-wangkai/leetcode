import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String findSpecialNodes(int n, int[][] edges) {
    Set<Integer> centers = findCenters(edges);

    Set<Integer>[] adjSets = buildAdjSets(edges);

    int[] distances = new int[n];
    Arrays.fill(distances, -1);
    for (int center : centers) {
      distances[center] = 0;
    }

    Queue<Integer> queue = new ArrayDeque<>();
    for (int center : centers) {
      queue.offer(center);
    }

    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjSets[head]) {
        if (distances[adj] == -1) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    int maxDistance = Arrays.stream(distances).max().getAsInt();

    return IntStream.range(0, distances.length)
        .map(i -> (distances[i] == maxDistance) ? 1 : 0)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining());
  }

  Set<Integer> findCenters(int[][] edges) {
    int n = edges.length + 1;

    Set<Integer>[] adjSets = buildAdjSets(edges);

    Set<Integer> leaves =
        IntStream.range(0, adjSets.length)
            .filter(i -> adjSets[i].size() == 1)
            .boxed()
            .collect(Collectors.toSet());

    int rest = n;
    while (rest > 2) {
      Set<Integer> nextLeaves = new HashSet<>();
      for (int leaf : leaves) {
        for (int adj : adjSets[leaf]) {
          adjSets[adj].remove(leaf);

          if (adjSets[adj].size() <= 1) {
            nextLeaves.add(adj);
          }
        }
      }

      rest -= leaves.size();
      leaves = nextLeaves;
    }

    return leaves;
  }

  Set<Integer>[] buildAdjSets(int[][] edges) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    return adjSets;
  }
}