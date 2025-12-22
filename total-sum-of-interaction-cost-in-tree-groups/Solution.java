import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long interactionCosts(int n, int[][] edges, int[] group) {
    return Arrays.stream(group)
            .distinct()
            .mapToLong(targetGroup -> computeInteractionCost(n, edges, group, targetGroup))
            .sum()
        / 2;
  }

  long computeInteractionCost(int n, int[][] edges, int[] group, int targetGroup) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    Outcome[] subOutcomes = new Outcome[n];
    buildSubOutcomes(subOutcomes, adjLists, group, targetGroup, -1, 0);

    int totalSize = (int) Arrays.stream(group).filter(g -> g == targetGroup).count();

    long[] superDistances = new long[n];
    buildSuperDistances(
        superDistances, adjLists, group, targetGroup, subOutcomes, totalSize, 0, -1, 0);

    return IntStream.range(0, group.length)
        .filter(i -> group[i] == targetGroup)
        .mapToLong(i -> subOutcomes[i].distance() + superDistances[i])
        .sum();
  }

  void buildSuperDistances(
      long[] superDistances,
      List<Integer>[] adjLists,
      int[] group,
      int targetGroup,
      Outcome[] subOutcomes,
      int totalSize,
      long superDistance,
      int parent,
      int node) {
    superDistances[node] = superDistance;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        long adjSuperDistance =
            superDistance
                + (subOutcomes[node].distance()
                    - (subOutcomes[adj].distance() + subOutcomes[adj].size()))
                + (totalSize - subOutcomes[adj].size());

        buildSuperDistances(
            superDistances,
            adjLists,
            group,
            targetGroup,
            subOutcomes,
            totalSize,
            adjSuperDistance,
            node,
            adj);
      }
    }
  }

  void buildSubOutcomes(
      Outcome[] subOutcomes,
      List<Integer>[] adjLists,
      int[] group,
      int targetGroup,
      int parent,
      int node) {
    int size = (group[node] == targetGroup) ? 1 : 0;
    long distance = 0;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildSubOutcomes(subOutcomes, adjLists, group, targetGroup, node, adj);

        size += subOutcomes[adj].size();
        distance += subOutcomes[adj].distance() + subOutcomes[adj].size();
      }
    }

    subOutcomes[node] = new Outcome(size, distance);
  }
}

record Outcome(int size, long distance) {}
