import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int[][] constructGridLayout(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    if (Arrays.stream(adjSets).anyMatch(adjSet -> adjSet.size() == 1)) {
      return constructLine(adjSets);
    }

    int[] corners =
        IntStream.range(0, adjSets.length).filter(i -> adjSets[i].size() == 2).toArray();
    for (int i = 0; i < corners.length; ++i) {
      for (int j = i + 1; j < corners.length; ++j) {
        if (adjSets[corners[i]].contains(corners[j])) {
          return constructStrip(adjSets, corners[i], corners[j]);
        }
      }
    }

    return constructBlock(adjSets);
  }

  int[][] constructStrip(Set<Integer>[] adjSets, int node1, int node2) {
    int n = adjSets.length;

    int[][] result = new int[2][n / 2];
    result[0][0] = node1;
    result[1][0] = node2;
    boolean[] visited = new boolean[n];
    visited[node1] = true;
    visited[node2] = true;
    for (int c = 1; c < n / 2; ++c) {
      for (int r = 0; r < result.length; ++r) {
        result[r][c] =
            adjSets[result[r][c - 1]].stream().filter(adj -> !visited[adj]).findAny().get();
        visited[result[r][c]] = true;
      }
    }

    return result;
  }

  int[][] constructBlock(Set<Integer>[] adjSets) {
    int n = adjSets.length;

    List<Integer> border = new ArrayList<>();
    border.add(
        IntStream.range(0, adjSets.length)
            .filter(i -> adjSets[i].size() == 2)
            .findAny()
            .getAsInt());
    boolean[] visited = new boolean[n];
    visited[border.get(0)] = true;
    while (true) {
      Optional<Integer> next =
          adjSets[border.getLast()].stream()
              .filter(i -> !visited[i] && (adjSets[i].size() == 3 || adjSets[i].size() == 2))
              .findAny();
      if (next.isEmpty()) {
        break;
      }

      border.add(next.get());
      visited[next.get()] = true;
    }

    int[] cornerIndices =
        IntStream.range(0, border.size()).filter(i -> adjSets[border.get(i)].size() == 2).toArray();

    int row = cornerIndices[1] + 1;
    int col = cornerIndices[2] - cornerIndices[1] + 1;

    int[][] result = new int[row][col];
    for (int r = 0; r < row; ++r) {
      result[r][0] = border.get(r);
    }
    for (int c = 1; c < col; ++c) {
      result[row - 1][c] = border.get(cornerIndices[1] + c);
    }
    for (int r = row - 2; r >= 0; --r) {
      result[r][col - 1] = border.get(cornerIndices[2] + (row - 1 - r));
    }
    for (int c = col - 2; c >= 1; --c) {
      result[0][c] = border.get(cornerIndices[3] + (col - 1 - c));
    }

    for (int r = 1; r <= row - 2; ++r) {
      for (int c = 1; c <= col - 2; ++c) {
        int r_ = r;
        int c_ = c;
        result[r][c] =
            adjSets[result[r - 1][c]].stream()
                .filter(adj -> !visited[adj] && adjSets[adj].contains(result[r_][c_ - 1]))
                .findAny()
                .get();
        visited[result[r][c]] = true;
      }
    }

    return result;
  }

  int[][] constructLine(Set<Integer>[] adjSets) {
    int n = adjSets.length;

    int[][] result = new int[1][n];
    result[0][0] =
        IntStream.range(0, adjSets.length).filter(i -> adjSets[i].size() == 1).findAny().getAsInt();
    boolean[] visited = new boolean[n];
    visited[result[0][0]] = true;
    for (int c = 0; c < n - 1; ++c) {
      int next = adjSets[result[0][c]].stream().filter(i -> !visited[i]).findAny().get();
      result[0][c + 1] = next;
      visited[next] = true;
    }

    return result;
  }
}