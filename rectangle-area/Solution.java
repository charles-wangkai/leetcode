class Solution {
  public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
    return getArea(ax1, ay1, ax2, ay2)
        + getArea(bx1, by1, bx2, by2)
        - getArea(Math.max(ax1, bx1), Math.max(ay1, by1), Math.min(ax2, bx2), Math.min(ay2, by2));
  }

  int getArea(int x1, int y1, int x2, int y2) {
    return Math.max(x2 - x1, 0) * Math.max(y2 - y1, 0);
  }
}
