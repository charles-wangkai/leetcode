class Solution {
  public int removeDuplicates(int[] A) {
    int length = 0;
    for (int i = 0; i < A.length; ++i) {
      if (length <= 1 || A[i] != A[length - 2]) {
        A[length] = A[i];
        ++length;
      }
    }

    return length;
  }
}
