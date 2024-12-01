import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
    List<Integer>[] adjLists2 = buildAdjLists(edges2);
    int targetNum2 =
        IntStream.range(0, adjLists2.length)
            .map(i -> search(adjLists2, k, 1, -1, i))
            .max()
            .getAsInt();

    List<Integer>[] adjLists1 = buildAdjLists(edges1);

    return IntStream.range(0, adjLists1.length)
        .map(i -> search(adjLists1, k, 0, -1, i) + targetNum2)
        .toArray();
  }

  List<Integer>[] buildAdjLists(int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[edges.length + 1];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return adjLists;
  }

  int search(List<Integer>[] adjLists, int k, int depth, int parent, int node) {
    int result = (depth <= k) ? 1 : 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result += search(adjLists, k, depth + 1, node, adj);
      }
    }

    return result;
  }
}