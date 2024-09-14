class Solution {
  public int tripletCount(int[] a, int[] b, int[] c) {
    int result = 0;
    for (int ai : a) {
      for (int bi : b) {
        for (int ci : c) {
          if (Integer.bitCount(ai ^ bi ^ ci) % 2 == 0) {
            ++result;
          }
        }
      }
    }

    return result;
  }
}