class Solution {
  public boolean checkIfExist(int[] arr) {
    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < arr.length; ++j) {
        if (j != i && arr[i] == arr[j] * 2) {
          return true;
        }
      }
    }

    return false;
  }
}