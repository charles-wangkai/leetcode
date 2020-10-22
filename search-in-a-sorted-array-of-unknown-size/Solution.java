// This is ArrayReader's API interface.
// You should not implement it, or speculate about its implementation
interface ArrayReader {
  public int get(int index);
}

class Solution {
  public int search(ArrayReader reader, int target) {
    int lowerIndex = 0;
    int upperIndex = Integer.MAX_VALUE;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;

      int number = reader.get(middleIndex);
      if (number == target) {
        return middleIndex;
      } else if (number < target) {
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return -1;
  }
}
