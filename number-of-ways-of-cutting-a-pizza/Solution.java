class Solution {
  static final int MODULUS = 1_000_000_007;

  public int ways(String[] pizza, int k) {
    int row = pizza.length;
    int col = pizza[0].length();

    boolean[][] rightApples = buildRightApples(pizza);
    boolean[][] downApples = buildDownApples(pizza);

    int[][][] dp = new int[row + 1][col + 1][k + 1];
    for (int restRow = 0; restRow <= row; ++restRow) {
      for (int restCol = 0; restCol <= col; ++restCol) {
        if (restRow == 0 || restCol == 0) {
          dp[restRow][restCol][0] = 1;
        } else {
          for (int restK = 1; restK <= k; ++restK) {
            boolean horizontalApple = false;
            for (int nextRestRow = restRow - 1; nextRestRow >= 0; --nextRestRow) {
              horizontalApple |= rightApples[row - nextRestRow - 1][col - restCol];
              if (horizontalApple) {
                dp[restRow][restCol][restK] =
                    addMod(dp[restRow][restCol][restK], dp[nextRestRow][restCol][restK - 1]);
              }
            }

            boolean verticalApple = false;
            for (int nextRestCol = restCol - 1; nextRestCol >= 1; --nextRestCol) {
              verticalApple |= downApples[row - restRow][col - nextRestCol - 1];
              if (verticalApple) {
                dp[restRow][restCol][restK] =
                    addMod(dp[restRow][restCol][restK], dp[restRow][nextRestCol][restK - 1]);
              }
            }
          }
        }
      }
    }

    return dp[row][col][k];
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  boolean[][] buildRightApples(String[] pizza) {
    int row = pizza.length;
    int col = pizza[0].length();

    boolean[][] rightApples = new boolean[row][col];
    for (int r = 0; r < row; ++r) {
      boolean rightApple = false;
      for (int c = col - 1; c >= 0; --c) {
        rightApple |= pizza[r].charAt(c) == 'A';
        rightApples[r][c] = rightApple;
      }
    }

    return rightApples;
  }

  boolean[][] buildDownApples(String[] pizza) {
    int row = pizza.length;
    int col = pizza[0].length();

    boolean[][] downApples = new boolean[row][col];
    for (int c = 0; c < col; ++c) {
      boolean downApple = false;
      for (int r = row - 1; r >= 0; --r) {
        downApple |= pizza[r].charAt(c) == 'A';
        downApples[r][c] = downApple;
      }
    }

    return downApples;
  }
}
