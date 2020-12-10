class Solution {
  public boolean validMountainArray(int[] A) {
    if (A.length < 3) {
      return false;
    }

    boolean down = false;
    for (int i = 0; i < A.length - 1; ++i) {
      if (A[i] == A[i + 1]) {
        return false;
      }

      if (down) {
        if (A[i] < A[i + 1]) {
          return false;
        }
      } else {
        if (A[i] > A[i + 1]) {
          if (i == 0) {
            return false;
          }

          down = true;
        }
      }
    }

    return down;
  }
}
