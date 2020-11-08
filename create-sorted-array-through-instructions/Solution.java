// https://en.wikipedia.org/wiki/Fenwick_tree

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 100000;

  public int createSortedArray(int[] instructions) {
    int result = 0;
    int[] A = new int[LIMIT + 1];
    for (int i = 0; i < instructions.length; ++i) {
      result = addMod(result, Math.min(sum(A, instructions[i] - 1), i - sum(A, instructions[i])));
      add(A, instructions[i], 1);
    }

    return result;
  }

  int LSB(int i) {
    return i & -i;
  }

  int sum(int[] A, int i) {
    int sum = 0;
    while (i > 0) {
      sum += A[i];
      i -= LSB(i);
    }

    return sum;
  }

  void add(int[] A, int i, int k) {
    while (i < A.length) {
      A[i] += k;
      i += LSB(i);
    }
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
