import java.util.ArrayList;
import java.util.List;

class Solution {
  public int maximumInvitations(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    Vertex[] leftVertices = new Vertex[m];
    for (int i = 0; i < leftVertices.length; ++i) {
      leftVertices[i] = new Vertex(i);
    }
    Vertex[] rightVertices = new Vertex[n];
    for (int i = 0; i < rightVertices.length; ++i) {
      rightVertices[i] = new Vertex(i);
    }
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (grid[i][j] == 1) {
          leftVertices[i].adjacents.add(j);
          rightVertices[j].adjacents.add(i);
        }
      }
    }

    int matchingCount = 0;
    for (int i = 0; i < leftVertices.length; ++i) {
      if (leftVertices[i].matching == -1
          && search(leftVertices, rightVertices, new boolean[rightVertices.length], i)) {
        ++matchingCount;
      }
    }

    return matchingCount;
  }

  static boolean search(
      Vertex[] leftVertices, Vertex[] rightVertices, boolean[] rightVisited, int leftIndex) {
    for (int rightIndex : leftVertices[leftIndex].adjacents) {
      if (!rightVisited[rightIndex]) {
        rightVisited[rightIndex] = true;

        if (rightVertices[rightIndex].matching == -1
            || search(
                leftVertices, rightVertices, rightVisited, rightVertices[rightIndex].matching)) {
          leftVertices[leftIndex].matching = rightIndex;
          rightVertices[rightIndex].matching = leftIndex;

          return true;
        }
      }
    }

    return false;
  }
}

class Vertex {
  int index;
  List<Integer> adjacents = new ArrayList<>();
  int matching = -1;

  Vertex(int index) {
    this.index = index;
  }
}
