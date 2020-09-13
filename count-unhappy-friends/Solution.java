import java.util.stream.IntStream;

class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] orders = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < preferences[i].length; ++j) {
                orders[i][preferences[i][j]] = j;
            }
        }

        int[] partners = new int[n];
        for (int[] pair : pairs) {
            partners[pair[0]] = pair[1];
            partners[pair[1]] = pair[0];
        }

        return (int) IntStream.range(0, n)
                .filter(i -> IntStream.range(0, n).anyMatch(
                        j -> j != i && orders[i][j] < orders[i][partners[i]] && orders[j][i] < orders[j][partners[j]]))
                .count();
    }
}