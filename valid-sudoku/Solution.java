public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!checkValid(board, i, 0, 9)) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (!checkValid(board, 0, j, 1)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!checkValid(board, i, j, 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkValid(char[][] board, int r, int c, int columnLimit) {
        boolean[] used = new boolean[10];
        int offsetR = 0;
        int offsetC = 0;
        for (int i = 0; i < 9; i++) {
            char cell = board[r + offsetR][c + offsetC];
            if (cell != '.') {
                int digit = cell - '0';
                if (used[digit]) {
                    return false;
                }
                used[digit] = true;
            }

            offsetC++;
            if (offsetC == columnLimit) {
                offsetR++;
                offsetC = 0;
            }
        }
        return true;
    }
}
