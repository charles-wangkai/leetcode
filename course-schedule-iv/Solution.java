import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] graph = new boolean[n][n];
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]][prerequisite[1]] = true;
        }

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }

        return Arrays.stream(queries).map(query -> graph[query[0]][query[1]]).collect(Collectors.toList());
    }
}