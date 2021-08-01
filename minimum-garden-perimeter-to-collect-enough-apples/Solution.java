class Solution {
  public long minimumPerimeter(long neededApples) {
    int halfSide = -1;
    int lower = 1;
    int upper = 100000;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (2L * middle * (middle + 1) * (2 * middle + 1) >= neededApples) {
        halfSide = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return halfSide * 8;
  }
}
