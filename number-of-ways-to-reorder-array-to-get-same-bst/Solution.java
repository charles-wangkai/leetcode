import java.math.BigInteger;

class Solution {
  static final int MODULUS = 1_000_000_007;

  int wayNum;

  public int numOfWays(int[] nums) {
    Node root = null;
    for (int i = 0; i < nums.length; ++i) {
      root = insert(root, nums[i]);
    }

    wayNum = 1;
    search(root);

    return addMod(wayNum, -1);
  }

  Node insert(Node node, int value) {
    if (node == null) {
      return new Node(value);
    }

    if (value < node.value) {
      node.left = insert(node.left, value);
    } else {
      node.right = insert(node.right, value);
    }

    return node;
  }

  int search(Node node) {
    if (node == null) {
      return 0;
    }

    int leftSize = search(node.left);
    int rightSize = search(node.right);

    wayNum = multiplyMod(wayNum, CMod(leftSize + rightSize, leftSize));

    return leftSize + rightSize + 1;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int CMod(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result =
          multiplyMod(
              result,
              multiplyMod(
                  n - i,
                  BigInteger.valueOf(i + 1).modInverse(BigInteger.valueOf(MODULUS)).intValue()));
    }

    return result;
  }
}

class Node {
  int value;
  Node left;
  Node right;

  Node(int value) {
    this.value = value;
  }
}
