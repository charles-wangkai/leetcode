import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int numberOfComponents(int[][] properties, int k) {
    int n = properties.length;

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    for (int i = 0; i < properties.length; ++i) {
      for (int j = i + 1; j < properties.length; ++j) {
        if (intersect(properties[i], properties[j]) >= k) {
          int rootI = findRoot(parents, i);
          int rootJ = findRoot(parents, j);
          if (rootI != rootJ) {
            parents[rootJ] = rootI;
          }
        }
      }
    }

    return (int) IntStream.range(0, n).map(i -> findRoot(parents, i)).distinct().count();
  }

  int intersect(int[] a, int[] b) {
    Set<Integer> aValues = Arrays.stream(a).boxed().collect(Collectors.toSet());

    return (int) Arrays.stream(b).filter(aValues::contains).distinct().count();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}