import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    int[] counts = new int[n];
    IntStream.concat(Arrays.stream(leftChild), Arrays.stream(rightChild))
        .filter(child -> child != -1)
        .forEach(child -> ++counts[child]);

    if (Arrays.stream(counts).filter(count -> count == 0).count() != 1
        || Arrays.stream(counts).filter(count -> count == 1).count() != n - 1) {
      return false;
    }

    int root = IntStream.range(0, counts.length).filter(i -> counts[i] == 0).findAny().getAsInt();
    boolean[] visited = new boolean[n];
    search(leftChild, rightChild, visited, root);

    return IntStream.range(0, visited.length).allMatch(i -> visited[i]);
  }

  void search(int[] leftChild, int[] rightChild, boolean[] visited, int node) {
    visited[node] = true;
    for (int child : new int[] {leftChild[node], rightChild[node]}) {
      if (child != -1) {
        search(leftChild, rightChild, visited, child);
      }
    }
  }
}
