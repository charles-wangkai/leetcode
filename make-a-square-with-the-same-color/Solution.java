class Solution {
  public boolean canMakeSquare(char[][] grid) {
    for (int beginR = 0; beginR <= 1; ++beginR) {
      for (int beginC = 0; beginC <= 1; ++beginC) {
        if (countWhite(grid, beginR, beginC) != 2) {
          return true;
        }
      }
    }

    return false;
  }

  int countWhite(char[][] grid, int beginR, int beginC) {
    int result = 0;
    for (int i = 0; i < 2; ++i) {
      for (int j = 0; j < 2; ++j) {
        if (grid[beginR + i][beginC + j] == 'W') {
          ++result;
        }
      }
    }

    return result;
  }
}