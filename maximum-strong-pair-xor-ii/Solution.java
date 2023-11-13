// https://leetcode.com/problems/maximum-strong-pair-xor-ii/solutions/4278110/xor-trie/

import java.util.Arrays;

class Solution {
  static final int MAX_BIT = 19;

  public int maximumStrongPairXor(int[] nums) {
    Arrays.sort(nums);

    int result = -1;
    Node xorTrie = new Node();
    int beginIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      insert(xorTrie, nums[i]);
      while (nums[beginIndex] * 2 < nums[i]) {
        remove(xorTrie, nums[beginIndex], MAX_BIT);
        ++beginIndex;
      }

      result = Math.max(result, maxXor(xorTrie, nums[i]));
    }

    return result;
  }

  int maxXor(Node xorTrie, int x) {
    int result = 0;
    Node node = xorTrie;
    for (int i = MAX_BIT; i >= 0; --i) {
      int bit = (x >> i) & 1;
      if (node.children[1 - bit] != null) {
        result |= 1 << i;
        node = node.children[1 - bit];
      } else {
        node = node.children[bit];
      }
    }

    return result;
  }

  boolean remove(Node node, int x, int b) {
    if (b == -1) {
      return true;
    }

    int bit = (x >> b) & 1;
    if (node.children[bit] != null && remove(node.children[bit], x, b - 1)) {
      node.children[bit] = null;
    }

    return node.children[0] == null && node.children[1] == null;
  }

  void insert(Node xorTrie, int x) {
    Node node = xorTrie;
    for (int i = MAX_BIT; i >= 0; --i) {
      int bit = (x >> i) & 1;
      if (node.children[bit] == null) {
        node.children[bit] = new Node();
      }
      node = node.children[bit];
    }
  }
}

class Node {
  Node[] children = new Node[2];
}
