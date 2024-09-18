class Solution {
  public long tripletCount(int[] a, int[] b, int[] c) {
    int[] aCounts = buildCounts(a);
    int[] bCounts = buildCounts(b);
    int[] cCounts = buildCounts(c);

    long result = 0;
    for (int i = 0; i < 2; ++i) {
      for (int j = 0; j < 2; ++j) {
        for (int k = 0; k < 2; ++k) {
          if ((i + j + k) % 2 == 0) {
            result += (long) aCounts[i] * bCounts[j] * cCounts[k];
          }
        }
      }
    }

    return result;
  }

  int[] buildCounts(int[] values) {
    int[] result = new int[2];
    for (int value : values) {
      ++result[Integer.bitCount(value) % 2];
    }

    return result;
  }
}