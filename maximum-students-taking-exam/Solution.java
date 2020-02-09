import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;

        int[][] maxStudentNums = new int[m][1 << n];
        for (int r = 0; r < m; ++r) {
            for (int state = 0; state < maxStudentNums[r].length; ++state) {
                boolean[] row = buildRow(n, state);
                if (!isValid(seats[r], row)) {
                    continue;
                }

                int maxPrevStudentNum = 0;
                if (r != 0) {
                    for (int prevState = 0; prevState < maxStudentNums[r - 1].length; ++prevState) {
                        boolean[] prevRow = buildRow(n, prevState);
                        if (!isValid(seats[r - 1], prevRow)) {
                            continue;
                        }

                        if (check(row, prevRow)) {
                            maxPrevStudentNum = Math.max(maxPrevStudentNum, maxStudentNums[r - 1][prevState]);
                        }
                    }
                }

                maxStudentNums[r][state] = maxPrevStudentNum + (int) IntStream.range(0, n).filter(i -> row[i]).count();
            }
        }

        return Arrays.stream(maxStudentNums[m - 1]).max().getAsInt();
    }

    boolean check(boolean[] row, boolean[] prevRow) {
        int n = row.length;

        return IntStream.range(0, n)
                .allMatch(i -> !prevRow[i] || ((i == 0 || !row[i - 1]) && (i == n - 1 || !row[i + 1])));
    }

    boolean isValid(char[] seatRow, boolean[] row) {
        int n = seatRow.length;

        return IntStream.range(0, n).allMatch(
                i -> !row[i] || (seatRow[i] == '.' && (i == 0 || !row[i - 1]) && (i == n - 1 || !row[i + 1])));
    }

    boolean[] buildRow(int size, int state) {
        boolean[] row = new boolean[size];
        for (int i = 0; i < size; ++i) {
            row[i] = (state & 1) != 0;
            state >>= 1;
        }

        return row;
    }
}