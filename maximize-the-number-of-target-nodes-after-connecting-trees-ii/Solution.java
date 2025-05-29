import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
    boolean[] blackOrWhites2 = fill(edges2);
    int blackNum2 =
        (int) IntStream.range(0, blackOrWhites2.length).filter(i -> blackOrWhites2[i]).count();
    int whiteNum2 = blackOrWhites2.length - blackNum2;
    int targetNum2 = Math.max(blackNum2, whiteNum2);

    boolean[] blackOrWhites1 = fill(edges1);
    int blackNum1 =
        (int) IntStream.range(0, blackOrWhites1.length).filter(i -> blackOrWhites1[i]).count();
    int whiteNum1 = blackOrWhites1.length - blackNum1;

    return IntStream.range(0, blackOrWhites1.length)
        .map(i -> (blackOrWhites1[i] ? blackNum1 : whiteNum1) + targetNum2)
        .toArray();
  }

  boolean[] fill(int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[edges.length + 1];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    boolean[] blackOrWhites = new boolean[adjLists.length];
    search(blackOrWhites, adjLists, -1, 0, true);

    return blackOrWhites;
  }

  void search(
      boolean[] blackOrWhites,
      List<Integer>[] adjLists,
      int parent,
      int node,
      boolean blackOrWhite) {
    blackOrWhites[node] = blackOrWhite;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(blackOrWhites, adjLists, node, adj, !blackOrWhite);
      }
    }
  }
}