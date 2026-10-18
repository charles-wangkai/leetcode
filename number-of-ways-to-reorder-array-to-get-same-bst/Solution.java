import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int numOfWays(int[] nums) {
    Node root = null;
    for (int i = 0; i < nums.length; ++i) {
      root = insert(root, nums[i]);
    }

    return MOD_INT.addMod(search(root).wayNum(), -1);
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

  Outcome search(Node node) {
    if (node == null) {
      return new Outcome(1, 0);
    }

    Outcome leftOutcome = search(node.left);
    Outcome rightOutcome = search(node.right);

    return new Outcome(
        MOD_INT.multiplyMod(
            MOD_INT.multiplyMod(leftOutcome.wayNum(), rightOutcome.wayNum()),
            CMod(
                leftOutcome.subtreeSize() + rightOutcome.subtreeSize(), leftOutcome.subtreeSize())),
        1 + leftOutcome.subtreeSize() + rightOutcome.subtreeSize());
  }

  int CMod(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = MOD_INT.multiplyMod(result, MOD_INT.divideMod(n - i, i + 1));
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

record Outcome(int wayNum, int subtreeSize) {}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
