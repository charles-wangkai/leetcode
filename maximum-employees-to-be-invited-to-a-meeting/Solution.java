// https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/discuss/1661037/Java-Detailed-explanation-with-picture.-O(n)-time-complexity

import java.util.ArrayList;
import java.util.List;

class Solution {
  public int maximumInvitations(int[] favorite) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[favorite.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }

    List<Pair> pairs = new ArrayList<>();
    for (int i = 0; i < favorite.length; ++i) {
      if (favorite[favorite[i]] == i) {
        if (i < favorite[i]) {
          pairs.add(new Pair(i, favorite[i]));
        }
      } else {
        adjLists[favorite[i]].add(i);
      }
    }

    int chainSum = 0;
    boolean[] visited = new boolean[favorite.length];
    for (Pair pair : pairs) {
      chainSum +=
          computeMaxChain(adjLists, visited, pair.v1) + computeMaxChain(adjLists, visited, pair.v2);
    }

    int maxCircle = 0;
    int[] counts = new int[favorite.length];
    int[] rounds = new int[favorite.length];
    int round = 1;
    for (int i = 0; i < favorite.length; ++i) {
      if (!visited[i] && counts[i] == 0) {
        int count = 1;
        int v = i;
        while (counts[v] == 0) {
          counts[v] = count;
          rounds[v] = round;

          v = favorite[v];
          ++count;
        }

        if (rounds[v] == round) {
          maxCircle = Math.max(maxCircle, count - counts[v]);
        }

        ++round;
      }
    }

    return Math.max(chainSum, maxCircle);
  }

  int computeMaxChain(List<Integer>[] adjLists, boolean[] visited, int v) {
    visited[v] = true;

    int maxSubResult = 0;
    for (int adj : adjLists[v]) {
      maxSubResult = Math.max(maxSubResult, computeMaxChain(adjLists, visited, adj));
    }

    return 1 + maxSubResult;
  }
}

class Pair {
  int v1;
  int v2;

  Pair(int v1, int v2) {
    this.v1 = v1;
    this.v2 = v2;
  }
}