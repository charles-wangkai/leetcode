// https://leetcode.com/problems/maximum-xor-of-two-non-overlapping-subtrees/solutions/2848513/two-vs-one-pass/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  static final int BIT_NUM = 46;

  public long maxXor(int n, int[][] edges, int[] values) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    long[] subtreeSums = new long[n];
    buildSubtreeSums(subtreeSums, values, adjLists, new boolean[n], 0);

    return search(adjLists, subtreeSums, new boolean[n], new Trie(), 0);
  }

  long search(
      List<Integer>[] adjLists, long[] subtreeSums, boolean[] visited, Trie trie, int node) {
    visited[node] = true;

    long result = computeMaxXor(subtreeSums[node], trie);
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        result = Math.max(result, search(adjLists, subtreeSums, visited, trie, adj));
      }
    }

    insert(trie, subtreeSums[node]);

    return result;
  }

  long computeMaxXor(long value, Trie trie) {
    if (trie.bitToChild.isEmpty()) {
      return 0;
    }

    long result = 0;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      int bit = (int) ((value >> i) & 1);
      if (trie.bitToChild.containsKey(1 - bit)) {
        result += 1L << i;
        trie = trie.bitToChild.get(1 - bit);
      } else {
        trie = trie.bitToChild.get(bit);
      }
    }

    return result;
  }

  void insert(Trie trie, long value) {
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      int bit = (int) ((value >> i) & 1);
      if (!trie.bitToChild.containsKey(bit)) {
        trie.bitToChild.put(bit, new Trie());
      }

      trie = trie.bitToChild.get(bit);
    }
  }

  void buildSubtreeSums(
      long[] subtreeSums, int[] values, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    subtreeSums[node] = values[node];
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        buildSubtreeSums(subtreeSums, values, adjLists, visited, adj);
        subtreeSums[node] += subtreeSums[adj];
      }
    }
  }
}

class Trie {
  Map<Integer, Trie> bitToChild = new HashMap<>();
}
