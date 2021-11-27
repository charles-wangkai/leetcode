class Solution {
  public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
    int result = 0;

    int r = startPos[0];
    while (r != homePos[0]) {
      if (r < homePos[0]) {
        ++r;
      } else {
        --r;
      }

      result += rowCosts[r];
    }

    int c = startPos[1];
    while (c != homePos[1]) {
      if (c < homePos[1]) {
        ++c;
      } else {
        --c;
      }

      result += colCosts[c];
    }

    return result;
  }
}