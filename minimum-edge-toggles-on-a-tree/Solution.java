import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    char[] colors = start.toCharArray();
    List<Integer> toggleEdges = new ArrayList<>();
    search(toggleEdges, edges, edgeLists, target, colors, -1, 0);
    Collections.sort(toggleEdges);

    return (colors[0] == target.charAt(0)) ? toggleEdges : List.of(-1);
  }

  void search(
      List<Integer> toggleEdges,
      int[][] edges,
      List<Integer>[] edgeLists,
      String target,
      char[] colors,
      int parentEdge,
      int node) {
    for (int edge : edgeLists[node]) {
      if (edge != parentEdge) {
        int other = (edges[edge][0] == node) ? edges[edge][1] : edges[edge][0];

        search(toggleEdges, edges, edgeLists, target, colors, edge, other);
      }
    }

    if (parentEdge != -1 && colors[node] != target.charAt(node)) {
      int parent = (edges[parentEdge][0] == node) ? edges[parentEdge][1] : edges[parentEdge][0];

      colors[node] = flip(colors[node]);
      colors[parent] = flip(colors[parent]);
      toggleEdges.add(parentEdge);
    }
  }

  char flip(char color) {
    return (char) ('0' + '1' - color);
  }
}