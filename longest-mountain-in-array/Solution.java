class Solution {
  public int longestMountain(int[] A) {
    int result = 0;
    int prevUp = 0;
    int up = 0;
    int down = 0;
    for (int i = 1; i < A.length; ++i) {
      if (A[i] > A[i - 1]) {
        ++up;
        down = 0;
      } else if (A[i] < A[i - 1]) {
        if (up != 0) {
          prevUp = up;
          up = 0;
        }

        ++down;

        if (prevUp != 0) {
          result = Math.max(result, prevUp + down + 1);
        }
      } else {
        prevUp = 0;
        up = 0;
        down = 0;
      }
    }

    return result;
  }
}
