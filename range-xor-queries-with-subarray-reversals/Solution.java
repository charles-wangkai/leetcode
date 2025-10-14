// https://leetcode.com/problems/range-xor-queries-with-subarray-reversals/solutions/6680768/java-implementation-using-avl-tree-beating-100/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Solution {
  public int[] getResults(int[] nums, int[][] queries) {
    List<Integer> result = new ArrayList<>();

    AugmentedAVLTree tree = new AugmentedAVLTree();
    for (int i = 0; i < nums.length; ++i) {
      tree.insert(i, nums[i]);
    }

    for (int[] query : queries) {
      int type = query[0];
      if (type == 1) {
        int index = query[1];
        int value = query[2];

        tree.update(index, value);
      } else if (type == 2) {
        int left = query[1];
        int right = query[2];

        result.add(tree.rangeXor(left, right));
      } else {
        int left = query[1];
        int right = query[2];

        tree.reverse(left, right);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}

class AugmentedAVLTree {
  Node root;

  void insert(int index, int value) {
    Node[] parts = split(root, index);

    root =
        merge(
            merge(parts[0], new Node(value, value, 1, new Random().nextInt(), false, null, null)),
            parts[1]);
  }

  int size(Node node) {
    return (node == null) ? 0 : node.size;
  }

  int xor(Node node) {
    return (node == null) ? 0 : node.xor;
  }

  void pushDown(Node node) {
    if (node != null && node.reversed) {
      node.reversed = false;

      Node temp = node.left;
      node.left = node.right;
      node.right = temp;

      if (node.left != null) {
        node.left.reversed ^= true;
      }
      if (node.right != null) {
        node.right.reversed ^= true;
      }
    }
  }

  void update(Node node) {
    if (node != null) {
      node.size = 1 + size(node.left) + size(node.right);
      node.xor = node.value ^ xor(node.left) ^ xor(node.right);
    }
  }

  Node[] split(Node node, int leftSize) {
    if (node == null) {
      return new Node[] {null, null};
    }

    pushDown(node);

    Node[] result;
    int currentLeftSize = size(node.left);
    if (leftSize <= currentLeftSize) {
      result = split(node.left, leftSize);

      node.left = result[1];
      result[1] = node;
    } else {
      result = split(node.right, leftSize - currentLeftSize - 1);

      node.right = result[0];
      result[0] = node;
    }

    update(node);

    return result;
  }

  Node merge(Node left, Node right) {
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }

    pushDown(left);
    pushDown(right);

    if (left.priority > right.priority) {
      left.right = merge(left.right, right);
      update(left);

      return left;
    }

    right.left = merge(left, right.left);
    update(right);

    return right;
  }

  void update(int index, int value) {
    Node[] parts1 = split(root, index);

    Node[] parts2 = split(parts1[1], 1);
    parts2[0].value = value;
    update(parts2[0]);

    root = merge(parts1[0], merge(parts2[0], parts2[1]));
  }

  int rangeXor(int left, int right) {
    Node[] parts1 = split(root, left);

    Node[] parts2 = split(parts1[1], right - left + 1);
    int result = xor(parts2[0]);

    root = merge(parts1[0], merge(parts2[0], parts2[1]));

    return result;
  }

  void reverse(int left, int right) {
    Node[] parts1 = split(root, left);

    Node[] parts2 = split(parts1[1], right - left + 1);
    parts2[0].reversed ^= true;

    root = merge(parts1[0], merge(parts2[0], parts2[1]));
  }
}

class Node {
  int value;
  int xor;
  int size;
  int priority;
  boolean reversed;
  Node left;
  Node right;

  Node(int value, int xor, int size, int priority, boolean reversed, Node left, Node right) {
    this.value = value;
    this.xor = xor;
    this.size = size;
    this.priority = priority;
    this.reversed = reversed;
    this.left = left;
    this.right = right;
  }
}
