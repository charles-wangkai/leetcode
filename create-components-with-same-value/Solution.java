import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
  public int componentValue(int[] nums, int[][] edges) {
    int sum = Arrays.stream(nums).sum();
    for (int value = 1; ; ++value) {
      if (sum % value == 0 && check(nums, edges, value)) {
        return sum / value - 1;
      }
    }
  }

  boolean check(int[] nums, int[][] edges, int value) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[nums.length];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    Queue<Integer> leaves = new ArrayDeque<>();
    for (int i = 0; i < adjSets.length; ++i) {
      if (adjSets[i].size() <= 1) {
        leaves.offer(i);
      }
    }

    int[] nodes = nums.clone();
    while (!leaves.isEmpty()) {
      int leaf = leaves.poll();
      if (nodes[leaf] > value) {
        return false;
      }

      if (adjSets[leaf].size() == 1) {
        int adj = adjSets[leaf].iterator().next();

        if (nodes[leaf] < value) {
          nodes[adj] += nodes[leaf];
        }

        adjSets[adj].remove(leaf);
        if (adjSets[adj].size() <= 1) {
          leaves.offer(adj);
        }
      }
    }

    return true;
  }
}
