class Solution {
  public int maxTurbulenceSize(int[] arr) {
    int maxSize = 1;
    int size = 1;
    Boolean prevUpOrDown = null;
    for (int i = 1; i < arr.length; ++i) {
      if (prevUpOrDown != null
          && ((prevUpOrDown && arr[i - 1] > arr[i]) || (!prevUpOrDown && arr[i - 1] < arr[i]))) {
        prevUpOrDown = !prevUpOrDown;
        ++size;
      } else if (arr[i - 1] > arr[i]) {
        prevUpOrDown = false;
        size = 2;
      } else if (arr[i - 1] < arr[i]) {
        prevUpOrDown = true;
        size = 2;
      } else {
        prevUpOrDown = null;
      }

      maxSize = Math.max(maxSize, size);
    }

    return maxSize;
  }
}
