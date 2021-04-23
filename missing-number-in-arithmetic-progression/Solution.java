class Solution {
  public int missingNumber(int[] arr) {
    int firstDelta = arr[1] - arr[0];
    int lastDelta = arr[arr.length - 1] - arr[arr.length - 2];

    int diff;
    if (firstDelta < 0) {
      diff = Math.max(firstDelta, lastDelta);
    } else if (firstDelta > 0) {
      diff = Math.min(firstDelta, lastDelta);
    } else {
      return arr[0];
    }

    for (int i = 0; ; ++i) {
      if (arr[i + 1] != arr[i] + diff) {
        return arr[i] + diff;
      }
    }
  }
}
