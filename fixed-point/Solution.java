class Solution {
  public int fixedPoint(int[] arr) {
    int result = -1;
    int lowerIndex = 0;
    int upperIndex = arr.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;

      if (arr[middleIndex] > middleIndex) {
        upperIndex = middleIndex - 1;
      } else if (arr[middleIndex] < middleIndex) {
        lowerIndex = middleIndex + 1;
      } else {
        result = middleIndex;
        upperIndex = middleIndex - 1;
      }
    }

    return result;
  }
}
