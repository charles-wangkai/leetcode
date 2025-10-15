// https://leetcode.com/problems/subarrays-with-xor-at-least-k/solutions/7026832/java-solution-with-prefix-sum-binary-tree/

class Solution {
  static final int BIT_NUM = 30;

  public long countXorSubarrays(int[] nums, int k) {
    TrieNode root = new TrieNode();
    insert(root, 0);

    long result = 0;
    int prefixXor = 0;
    for (int num : nums) {
      prefixXor ^= num;
      result += query(k, root, prefixXor);
      insert(root, prefixXor);
    }

    return result;
  }

  void insert(TrieNode root, int prefixXor) {
    TrieNode node = root;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      int bit = (prefixXor >> b) & 1;
      if (node.children[bit] == null) {
        node.children[bit] = new TrieNode();
      }
      node = node.children[bit];
      ++node.count;
    }
  }

  int query(int k, TrieNode root, int prefixXor) {
    int result = 0;
    TrieNode node = root;
    for (int b = BIT_NUM - 1; b >= 0 && node != null; --b) {
      int pBit = (prefixXor >> b) & 1;
      int kBit = (k >> b) & 1;

      if (kBit == 1) {
        node = node.children[1 - pBit];
      } else {
        if (node.children[1 - pBit] != null) {
          result += node.children[1 - pBit].count;
        }

        node = node.children[pBit];
      }
    }
    if (node != null) {
      result += node.count;
    }

    return result;
  }
}

class TrieNode {
  int count;
  TrieNode[] children = new TrieNode[2];
}
