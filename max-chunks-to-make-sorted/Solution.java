class Solution {
  public int maxChunksToSorted(int[] arr) {
    int result = 0;
    int maxValue = -1;
    for (int i = 0; i < arr.length; ++i) {
      maxValue = Math.max(maxValue, arr[i]);
      if (maxValue == i) {
        ++result;
      }
    }

    return result;
  }
}
