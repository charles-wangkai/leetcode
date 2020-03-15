import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] rowMins = new int[m];
        Arrays.fill(rowMins, Integer.MAX_VALUE);

        int[] colMaxs = new int[n];
        Arrays.fill(colMaxs, Integer.MIN_VALUE);

        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                rowMins[r] = Math.min(rowMins[r], matrix[r][c]);
                colMaxs[c] = Math.max(colMaxs[c], matrix[r][c]);
            }
        }

        return IntStream.range(0, m)
                .flatMap(r -> IntStream.range(0, n)
                        .filter(c -> matrix[r][c] == rowMins[r] && matrix[r][c] == colMaxs[c]).map(c -> matrix[r][c]))
                .boxed().collect(Collectors.toList());
    }
}