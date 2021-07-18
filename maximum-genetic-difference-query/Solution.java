// https://leetcode.com/problems/maximum-genetic-difference-query/discuss/1344913

import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] maxGeneticDifference(int[] parents, int[][] queries) {
    int n = parents.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childrenLists = new List[n];
    for (int i = 0; i < childrenLists.length; ++i) {
      childrenLists[i] = new ArrayList<>();
    }

    int root = -1;
    for (int i = 0; i < parents.length; ++i) {
      if (parents[i] == -1) {
        root = i;
      } else {
        childrenLists[parents[i]].add(i);
      }
    }

    @SuppressWarnings("unchecked")
    List<Element>[] queriesByNode = new List[n];
    for (int i = 0; i < queriesByNode.length; ++i) {
      queriesByNode[i] = new ArrayList<>();
    }

    for (int i = 0; i < queries.length; ++i) {
      queriesByNode[queries[i][0]].add(new Element(i, queries[i][1]));
    }

    int[] queryResults = new int[queries.length];
    Node trie = new Node();
    dfs(queryResults, childrenLists, queriesByNode, trie, root);

    return queryResults;
  }

  void dfs(
      int[] queryResults,
      List<Integer>[] childrenLists,
      List<Element>[] queriesByNode,
      Node trie,
      int u) {
    trie.increase(u, 1);

    for (Element e : queriesByNode[u]) {
      queryResults[e.index] = trie.findMax(e.val);
    }

    for (int child : childrenLists[u]) {
      dfs(queryResults, childrenLists, queriesByNode, trie, child);
    }

    trie.increase(u, -1);
  }
}

class Element {
  int index;
  int val;

  Element(int index, int val) {
    this.index = index;
    this.val = val;
  }
}

class Node {
  static final int BIT_NUM = 18;

  Node[] children = new Node[2];
  int count = 0;

  void increase(int number, int d) {
    Node curr = this;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      int bit = ((number & (1 << i)) == 0) ? 0 : 1;
      if (curr.children[bit] == null) {
        curr.children[bit] = new Node();
      }

      curr = curr.children[bit];
      curr.count += d;
    }
  }

  int findMax(int number) {
    int result = 0;
    Node curr = this;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      int bit = ((number & (1 << i)) == 0) ? 0 : 1;
      if (curr.children[1 - bit] != null && curr.children[1 - bit].count != 0) {
        curr = curr.children[1 - bit];
        result += 1 << i;
      } else {
        curr = curr.children[bit];
      }
    }

    return result;
  }
}
