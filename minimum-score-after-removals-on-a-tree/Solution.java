import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minimumScore(int[] nums, int[][] edges) {
    int n = nums.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] xors = new int[n];
    boolean[][] parents = new boolean[n][n];
    search(nums, adjLists, xors, parents, new ArrayList<>(), 0);

    int result = Integer.MAX_VALUE;
    for (int i = 1; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        if (parents[i][j]) {
          result = Math.min(result, computeScore(xors[0] ^ xors[j], xors[j] ^ xors[i], xors[i]));
        } else if (parents[j][i]) {
          result = Math.min(result, computeScore(xors[0] ^ xors[i], xors[i] ^ xors[j], xors[j]));
        } else {
          result = Math.min(result, computeScore(xors[0] ^ xors[i] ^ xors[j], xors[i], xors[j]));
        }
      }
    }

    return result;
  }

  int computeScore(int xor1, int xor2, int xor3) {
    int[] sorted = IntStream.of(xor1, xor2, xor3).sorted().toArray();

    return sorted[sorted.length - 1] - sorted[0];
  }

  int search(
      int[] nums,
      List<Integer>[] adjLists,
      int[] xors,
      boolean[][] parents,
      List<Integer> path,
      int node) {
    int parent = path.isEmpty() ? -1 : path.getLast();

    for (int p : path) {
      parents[node][p] = true;
    }

    path.add(node);

    xors[node] = nums[node];
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        xors[node] ^= search(nums, adjLists, xors, parents, path, adj);
      }
    }

    path.removeLast();

    return xors[node];
  }
}