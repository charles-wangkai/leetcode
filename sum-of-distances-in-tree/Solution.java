import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    Node[] nodes = IntStream.range(0, n).mapToObj(i -> new Node()).toArray(Node[]::new);

    for (int[] edge : edges) {
      nodes[edge[0]].adjs.add(edge[1]);
      nodes[edge[1]].adjs.add(edge[0]);
    }

    postOrderDfs(nodes, new boolean[n], 0);

    nodes[0].distanceSum = nodes[0].subNodeDistanceSum;
    preOrderDfs(nodes, new boolean[n], 0);

    return Arrays.stream(nodes).mapToInt(node -> node.distanceSum).toArray();
  }

  void postOrderDfs(Node[] nodes, boolean[] visited, int index) {
    visited[index] = true;

    nodes[index].subNodeCount = 1;
    for (int adj : nodes[index].adjs) {
      if (!visited[adj]) {
        postOrderDfs(nodes, visited, adj);

        nodes[index].subNodeCount += nodes[adj].subNodeCount;
        nodes[index].subNodeDistanceSum += nodes[adj].subNodeDistanceSum + nodes[adj].subNodeCount;
      }
    }
  }

  void preOrderDfs(Node[] nodes, boolean[] visited, int index) {
    visited[index] = true;

    for (int adj : nodes[index].adjs) {
      if (!visited[adj]) {
        nodes[adj].distanceSum =
            nodes[index].distanceSum
                - nodes[adj].subNodeCount
                + (nodes.length - nodes[adj].subNodeCount);

        preOrderDfs(nodes, visited, adj);
      }
    }
  }
}

class Node {
  List<Integer> adjs = new ArrayList<>();
  int subNodeCount;
  int subNodeDistanceSum;
  int distanceSum;
}
