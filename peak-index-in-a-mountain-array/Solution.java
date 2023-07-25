class Solution {
  public int peakIndexInMountainArray(int[] arr) {
    int lowerIndex = 1;
    int upperIndex = arr.length - 2;
    while (true) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (arr[middleIndex] < arr[middleIndex + 1]) {
        lowerIndex = middleIndex + 1;
      } else if (arr[middleIndex - 1] > arr[middleIndex]) {
        upperIndex = middleIndex - 1;
      } else {
        return middleIndex;
      }
    }
  }
}
