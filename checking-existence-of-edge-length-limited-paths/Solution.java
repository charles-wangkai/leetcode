import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
    Arrays.sort(edgeList, Comparator.comparing(edge -> edge[2]));
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i][2]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    boolean[] result = new boolean[queries.length];
    int edgeIndex = 0;
    for (int queryIndex : sortedQueryIndices) {
      int[] query = queries[queryIndex];
      while (edgeIndex != edgeList.length && edgeList[edgeIndex][2] < query[2]) {
        int root1 = findRoot(parents, edgeList[edgeIndex][0]);
        int root2 = findRoot(parents, edgeList[edgeIndex][1]);
        if (root1 != root2) {
          parents[root2] = root1;
        }

        ++edgeIndex;
      }

      result[queryIndex] = findRoot(parents, query[0]) == findRoot(parents, query[1]);
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
