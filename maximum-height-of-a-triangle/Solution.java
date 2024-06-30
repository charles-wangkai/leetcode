class Solution {
  public int maxHeightOfTriangle(int red, int blue) {
    return Math.max(computeHeight(1, red, blue), computeHeight(1, blue, red));
  }

  int computeHeight(int width, int ball1, int ball2) {
    return (ball1 >= width) ? (1 + computeHeight(width + 1, ball2, ball1 - width)) : 0;
  }
}