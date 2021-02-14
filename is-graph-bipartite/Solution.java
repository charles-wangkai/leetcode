class Solution {
  public boolean isBipartite(int[][] graph) {
    int[] colors = new int[graph.length];

    for (int i = 0; i < colors.length; ++i) {
      if (colors[i] == 0 && !paint(graph, colors, i, 1)) {
        return false;
      }
    }

    return true;
  }

  boolean paint(int[][] graph, int[] colors, int index, int color) {
    if (colors[index] != 0) {
      return colors[index] == color;
    }

    colors[index] = color;
    for (int adj : graph[index]) {
      if (!paint(graph, colors, adj, -color)) {
        return false;
      }
    }

    return true;
  }
}
