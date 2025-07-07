class Solution {
  public int minMoves(int sx, int sy, int tx, int ty) {
    if (sx > sy) {
      return minMoves(sy, sx, ty, tx);
    }

    int result = 0;
    while (true) {
      if (tx == sx && ty == sy) {
        return result;
      }
      if (tx < sx || ty < sy) {
        return -1;
      }

      if (tx >= ty) {
        if (tx >= 2 * ty) {
          if (tx % 2 == 1) {
            return -1;
          }

          tx /= 2;
        } else {
          tx -= ty;
        }
      } else if (ty >= 2 * tx) {
        if (ty % 2 == 1) {
          return -1;
        }

        ty /= 2;
      } else {
        ty -= tx;
      }

      ++result;
    }
  }
}
