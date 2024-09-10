class Solution {
  static final int MODULUS = 1_000_000_007;

  public int getPermutationIndex(int[] perm) {
    int[] p = new int[perm.length + 1];
    p[0] = 1;
    for (int i = 1; i < p.length; ++i) {
      p[i] = multiplyMod(i, p[i - 1]);
    }

    int result = 0;
    int[] binaryIndexedTree = new int[Integer.highestOneBit(perm.length) * 2 + 1];
    for (int i = 0; i < perm.length; ++i) {
      result =
          addMod(
              result,
              multiplyMod(
                  perm[i] - 1 - query(binaryIndexedTree, perm[i] - 1), p[perm.length - 1 - i]));
      update(binaryIndexedTree, perm[i], 1);
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result += binaryIndexedTree[index];
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] += delta;
      index += index & -index;
    }
  }
}