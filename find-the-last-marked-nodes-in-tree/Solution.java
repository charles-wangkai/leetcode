import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[] lastMarkedNodes(int[][] edges) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    Farthest[] subtreeFarthests = new Farthest[n];
    buildSubtreeFarthests(subtreeFarthests, adjLists, -1, 0);

    int[] lastMarked = new int[n];
    buildLastMarked(lastMarked, adjLists, subtreeFarthests, new Farthest(0, 0), -1, 0);

    return lastMarked;
  }

  void buildLastMarked(
      int[] lastMarked,
      List<Integer>[] adjLists,
      Farthest[] subtreeFarthests,
      Farthest upFarthest,
      int parent,
      int node) {
    lastMarked[node] = merge(upFarthest, subtreeFarthests[node]).node();

    List<Farthest> farthests = new ArrayList<>();
    farthests.add(upFarthest);
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        farthests.add(moveAway(subtreeFarthests[adj]));
      }
    }
    Collections.sort(farthests, Comparator.comparing(Farthest::distance).reversed());

    for (int adj : adjLists[node]) {
      if (adj != parent) {

        buildLastMarked(
            lastMarked,
            adjLists,
            subtreeFarthests,
            moveAway(
                farthests.get(moveAway(subtreeFarthests[adj]).equals(farthests.get(0)) ? 1 : 0)),
            node,
            adj);
      }
    }
  }

  void buildSubtreeFarthests(
      Farthest[] subtreeFarthests, List<Integer>[] adjLists, int parent, int node) {
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildSubtreeFarthests(subtreeFarthests, adjLists, node, adj);

        subtreeFarthests[node] = merge(subtreeFarthests[node], moveAway(subtreeFarthests[adj]));
      }
    }

    if (subtreeFarthests[node] == null) {
      subtreeFarthests[node] = new Farthest(0, node);
    }
  }

  Farthest merge(Farthest f1, Farthest f2) {
    if (f1 == null) {
      return f2;
    }
    if (f2 == null) {
      return f1;
    }

    return (f1.distance() >= f2.distance()) ? f1 : f2;
  }

  Farthest moveAway(Farthest f) {
    return new Farthest(f.distance() + 1, f.node());
  }
}

record Farthest(int distance, int node) {}
