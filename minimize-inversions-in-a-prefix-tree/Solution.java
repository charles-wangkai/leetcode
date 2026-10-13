// https://leetcode.com/problems/minimize-inversions-in-a-prefix-tree/solutions/8568253/step-by-step-walkthrough-easy-to-underst-mf13/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  int total;

  public int minInversions(String[] words, int[] target) {
    Map<Integer, Integer> indexToPos =
        IntStream.range(0, target.length).boxed().collect(Collectors.toMap(i -> target[i], i -> i));

    TrieNode root = new TrieNode();
    for (int i = 0; i < words.length; ++i) {
      TrieNode node = root;
      for (char letter : words[i].toCharArray()) {
        node.letterToChild.putIfAbsent(letter, new TrieNode());
        node = node.letterToChild.get(letter);
      }

      node.pos = indexToPos.get(i);
    }

    total = 0;
    search(root);

    return total;
  }

  List<Integer> search(TrieNode node) {
    List<List<Integer>> posLists = new ArrayList<>();
    for (TrieNode child : node.letterToChild.values()) {
      posLists.add(search(child));
    }

    if (node.pos != -1) {
      for (List<Integer> posList : posLists) {
        total += -1 - Collections.binarySearch(posList, node.pos);
      }
    }

    int k = posLists.size();

    int[][] costs = new int[k][k];
    for (int i = 0; i < k; ++i) {
      for (int j = 0; j < k; ++j) {
        if (j != i) {
          int index = 0;
          for (int x : posLists.get(i)) {
            while (index != posLists.get(j).size() && posLists.get(j).get(index) < x) {
              ++index;
            }

            costs[i][j] += index;
          }
        }
      }
    }

    int[] dp = new int[1 << k];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int mask = 0; mask < dp.length; ++mask) {
      for (int j = 0; j < k; ++j) {
        if (((mask >> j) & 1) == 0) {
          int added = 0;
          for (int i = 0; i < k; ++i) {
            if (((mask >> i) & 1) == 1) {
              added += costs[i][j];
            }
          }

          int nextMask = mask | (1 << j);
          dp[nextMask] = Math.min(dp[nextMask], dp[mask] + added);
        }
      }
    }

    total += dp[dp.length - 1];

    List<Integer> result = new ArrayList<>();
    if (node.pos != -1) {
      result.add(node.pos);
    }
    for (List<Integer> posList : posLists) {
      result.addAll(posList);
    }
    Collections.sort(result);

    return result;
  }
}

class TrieNode {
  Map<Character, TrieNode> letterToChild = new HashMap<>();
  int pos = -1;
}
