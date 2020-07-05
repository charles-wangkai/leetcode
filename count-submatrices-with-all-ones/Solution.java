class Solution {
    public int numSubmat(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int result = 0;
        for (int beginR = 0; beginR < row; ++beginR) {
            int heights[] = new int[col];
            for (int endR = beginR; endR < row; ++endR) {
                for (int c = 0; c < col; ++c) {
                    if (mat[endR][c] == 1) {
                        ++heights[c];
                    }
                }

                int count = 0;
                for (int c = 0; c <= col; ++c) {
                    if (c != col && heights[c] == endR - beginR + 1) {
                        ++count;
                    } else {
                        result += count + count * (count - 1) / 2;
                        count = 0;
                    }
                }
            }
        }

        return result;
    }
}