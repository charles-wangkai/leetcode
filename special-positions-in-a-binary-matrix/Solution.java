class Solution {
    public int numSpecial(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int[] rowOneCounts = new int[row];
        int[] colOneCounts = new int[col];
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (mat[r][c] == 1) {
                    ++rowOneCounts[r];
                    ++colOneCounts[c];
                }
            }
        }

        int result = 0;
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (mat[r][c] == 1 && rowOneCounts[r] == 1 && colOneCounts[c] == 1) {
                    ++result;
                }
            }
        }

        return result;
    }
}