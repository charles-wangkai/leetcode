// Definition for a binary tree node.

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int maxProduct(TreeNode root) {
    List<Integer> subtreeSums = new ArrayList<>();
    search(subtreeSums, root);

    int total = subtreeSums.stream().mapToInt(Integer::intValue).max().getAsInt();

    return MOD_INT.mod(
        subtreeSums.stream()
            .mapToLong(subtreeSum -> (long) subtreeSum * (total - subtreeSum))
            .max()
            .getAsLong());
  }

  int search(List<Integer> subtreeSums, TreeNode node) {
    if (node == null) {
      return 0;
    }

    int subtreeSum = node.val + search(subtreeSums, node.left) + search(subtreeSums, node.right);
    subtreeSums.add(subtreeSum);

    return subtreeSum;
  }
}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return (int) (x % modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, modulus);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, modulus);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, int exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
