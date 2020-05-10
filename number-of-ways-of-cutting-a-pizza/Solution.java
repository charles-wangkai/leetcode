class Solution {
    static final int MODULUS = 1_000_000_007;

    public int ways(String[] pizza, int k) {
        int row = pizza.length;
        int col = pizza[0].length();

        boolean[][] rightApples = buildRightApples(pizza);
        boolean[][] downApples = buildDownApples(pizza);

        int[][][] wayNums = new int[row + 1][col + 1][k + 1];
        for (int restRow = 0; restRow <= row; ++restRow) {
            for (int restCol = 0; restCol <= col; ++restCol) {
                for (int restK = 1; restK <= k; ++restK) {
                    if (restRow == 0 || restCol == 0) {
                        wayNums[restRow][restCol][0] = 1;
                    } else {
                        boolean horizontalApple = false;
                        for (int nextRestRow = restRow - 1; nextRestRow >= 0; --nextRestRow) {
                            horizontalApple |= rightApples[row - nextRestRow - 1][col - restCol];
                            if (horizontalApple) {
                                wayNums[restRow][restCol][restK] = addMod(wayNums[restRow][restCol][restK],
                                        wayNums[nextRestRow][restCol][restK - 1]);
                            }
                        }

                        boolean verticalApple = false;
                        for (int nextRestCol = restCol - 1; nextRestCol >= 1; --nextRestCol) {
                            verticalApple |= downApples[row - restRow][col - nextRestCol - 1];
                            if (verticalApple) {
                                wayNums[restRow][restCol][restK] = addMod(wayNums[restRow][restCol][restK],
                                        wayNums[restRow][nextRestCol][restK - 1]);
                            }
                        }
                    }
                }
            }
        }

        return wayNums[row][col][k];
    }

    static int addMod(int x, int y) {
        return (x + y) % MODULUS;
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