class Solution {
  static final int LIMIT = 60000;
  static final int OFFSET = 30000;

  public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
    long result = 0;
    int[] A = new int[1 + Integer.highestOneBit(LIMIT) * 2];
    for (int i = 0; i < nums1.length; ++i) {
      int delta = nums1[i] - nums2[i];
      result += prefix_sum(A, delta + diff + OFFSET);
      add(A, delta + OFFSET, 1);
    }

    return result;
  }

  int prefix_sum(int[] A, int i) {
    int sum = A[0];
    for (; i != 0; i -= LSB(i)) sum += A[i];
    return sum;
  }

  void add(int[] A, int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }

  int LSB(int x) {
    return x & -x;
  }
}
