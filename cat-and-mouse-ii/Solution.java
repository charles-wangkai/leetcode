class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
    int row = grid.length;
    int col = grid[0].length();

    int mouseR = -1;
    int mouseC = -1;
    int catR = -1;
    int catC = -1;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r].charAt(c) == 'M') {
          mouseR = r;
          mouseC = c;
        } else if (grid[r].charAt(c) == 'C') {
          catR = r;
          catC = c;
        }
      }
    }

    return search(
        grid,
        mouseJump,
        catJump,
        row * col * 2,
        new Boolean[row][col][row][col][row * col * 2],
        mouseR,
        mouseC,
        catR,
        catC,
        0);
  }

  boolean search(
      String[] grid,
      int mouseJump,
      int catJump,
      int maxTurn,
      Boolean[][][][][] states,
      int mouseR,
      int mouseC,
      int catR,
      int catC,
      int turn) {
    int row = grid.length;
    int col = grid[0].length();

    if (turn == maxTurn) {
      return false;
    }

    if (states[mouseR][mouseC][catR][catC][turn] == null) {
      if (turn % 2 == 0) {
        if (grid[catR].charAt(catC) == 'F' || (catR == mouseR && catC == mouseC)) {
          states[mouseR][mouseC][catR][catC][turn] = false;
        } else {
          boolean state = false;

          for (int i = 0; i < R_OFFSETS.length; ++i) {
            for (int jump = 0; jump <= mouseJump; ++jump) {
              int nextMouseR = mouseR + R_OFFSETS[i] * jump;
              int nextMouseC = mouseC + C_OFFSETS[i] * jump;
              if (!(nextMouseR >= 0
                  && nextMouseR < row
                  && nextMouseC >= 0
                  && nextMouseC < col
                  && grid[nextMouseR].charAt(nextMouseC) != '#')) {
                break;
              }

              if (search(
                  grid,
                  mouseJump,
                  catJump,
                  maxTurn,
                  states,
                  nextMouseR,
                  nextMouseC,
                  catR,
                  catC,
                  turn + 1)) {
                state = true;
              }
            }
          }

          states[mouseR][mouseC][catR][catC][turn] = state;
        }
      } else {
        if (grid[mouseR].charAt(mouseC) == 'F') {
          states[mouseR][mouseC][catR][catC][turn] = true;
        } else {
          boolean state = true;

          for (int i = 0; i < R_OFFSETS.length; ++i) {
            for (int jump = 0; jump <= catJump; ++jump) {
              int nextCatR = catR + R_OFFSETS[i] * jump;
              int nextCatC = catC + C_OFFSETS[i] * jump;
              if (!(nextCatR >= 0
                  && nextCatR < row
                  && nextCatC >= 0
                  && nextCatC < col
                  && grid[nextCatR].charAt(nextCatC) != '#')) {
                break;
              }

              if (!search(
                  grid, mouseJump, catJump, maxTurn, states, mouseR, mouseC, nextCatR, nextCatC,
                  turn + 1)) {
                state = false;
              }
            }
          }

          states[mouseR][mouseC][catR][catC][turn] = state;
        }
      }
    }

    return states[mouseR][mouseC][catR][catC][turn];
  }
}
