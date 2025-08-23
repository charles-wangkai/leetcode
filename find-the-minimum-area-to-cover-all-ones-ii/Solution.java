class Solution {
  public int minimumSum(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Span[][] topLefts = buildTopLefts(grid);
    Span[][] topRights = buildTopRights(grid);
    Span[][] bottomLefts = buildBottomLefts(grid);
    Span[][] bottomRights = buildBottomRights(grid);

    int result = Integer.MAX_VALUE;
    for (int top = 1; top < row; ++top) {
      for (int left = 1; left < col; ++left) {
        result =
            Math.min(
                result,
                Math.min(
                    computeArea(
                        topLefts[top - 1][left - 1],
                        topRights[top - 1][left],
                        bottomLefts[top][col - 1]),
                    computeArea(
                        topLefts[top - 1][col - 1],
                        bottomLefts[top][left - 1],
                        bottomRights[top][left])));
      }
    }
    for (int left = 1; left < col; ++left) {
      for (int top = 1; top < row; ++top) {
        result =
            Math.min(
                result,
                Math.min(
                    computeArea(
                        topLefts[top - 1][left - 1],
                        bottomLefts[top][left - 1],
                        topRights[row - 1][left]),
                    computeArea(
                        topLefts[row - 1][left - 1],
                        topRights[top - 1][left],
                        bottomRights[top][left])));
      }
    }
    for (int beginR = 1; beginR <= row - 2; ++beginR) {
      Span span = null;
      for (int endR = beginR; endR <= row - 2; ++endR) {
        for (int c = 0; c < col; ++c) {
          span = merge(span, (grid[endR][c] == 1) ? new Span(endR, endR, c, c) : null);
        }

        result =
            Math.min(
                result,
                computeArea(topLefts[beginR - 1][col - 1], span, bottomLefts[endR + 1][col - 1]));
      }
    }
    for (int beginC = 1; beginC <= col - 2; ++beginC) {
      Span span = null;
      for (int endC = beginC; endC <= col - 2; ++endC) {
        for (int r = 0; r < row; ++r) {
          span = merge(span, (grid[r][endC] == 1) ? new Span(r, r, endC, endC) : null);
        }

        result =
            Math.min(
                result,
                computeArea(topLefts[row - 1][beginC - 1], span, topRights[row - 1][endC + 1]));
      }
    }

    return result;
  }

  Span[][] buildTopLefts(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Span[][] result = new Span[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        result[r][c] =
            merge(
                merge((r == 0) ? null : result[r - 1][c], (c == 0) ? null : result[r][c - 1]),
                (grid[r][c] == 1) ? new Span(r, r, c, c) : null);
      }
    }

    return result;
  }

  Span[][] buildTopRights(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Span[][] result = new Span[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = col - 1; c >= 0; --c) {
        result[r][c] =
            merge(
                merge((r == 0) ? null : result[r - 1][c], (c == col - 1) ? null : result[r][c + 1]),
                (grid[r][c] == 1) ? new Span(r, r, c, c) : null);
      }
    }

    return result;
  }

  Span[][] buildBottomLefts(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Span[][] result = new Span[row][col];
    for (int r = row - 1; r >= 0; --r) {
      for (int c = 0; c < col; ++c) {
        result[r][c] =
            merge(
                merge((r == row - 1) ? null : result[r + 1][c], (c == 0) ? null : result[r][c - 1]),
                (grid[r][c] == 1) ? new Span(r, r, c, c) : null);
      }
    }

    return result;
  }

  Span[][] buildBottomRights(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Span[][] result = new Span[row][col];
    for (int r = row - 1; r >= 0; --r) {
      for (int c = col - 1; c >= 0; --c) {
        result[r][c] =
            merge(
                merge(
                    (r == row - 1) ? null : result[r + 1][c],
                    (c == col - 1) ? null : result[r][c + 1]),
                (grid[r][c] == 1) ? new Span(r, r, c, c) : null);
      }
    }

    return result;
  }

  Span merge(Span span1, Span span2) {
    if (span1 == null) {
      return span2;
    }
    if (span2 == null) {
      return span1;
    }

    return new Span(
        Math.min(span1.minR(), span2.minR()),
        Math.max(span1.maxR(), span2.maxR()),
        Math.min(span1.minC(), span2.minC()),
        Math.max(span1.maxC(), span2.maxC()));
  }

  int computeArea(Span span1, Span span2, Span span3) {
    int result = 0;
    for (Span span : new Span[] {span1, span2, span3}) {
      if (span == null) {
        return Integer.MAX_VALUE;
      }

      result += (span.maxR() - span.minR() + 1) * (span.maxC() - span.minC() + 1);
    }

    return result;
  }
}

record Span(int minR, int maxR, int minC, int maxC) {}
