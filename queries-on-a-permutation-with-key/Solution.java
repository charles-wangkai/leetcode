import java.util.stream.IntStream;

class Solution {
    public int[] processQueries(int[] queries, int m) {
        int[] result = new int[queries.length];
        int[] P = IntStream.rangeClosed(1, m).toArray();
        for (int i = 0; i < queries.length; ++i) {
            int index = 0;
            while (P[index] != queries[i]) {
                ++index;
            }

            result[i] = index;
            for (int j = index; j >= 1; --j) {
                P[j] = P[j - 1];
            }
            P[0] = queries[i];
        }

        return result;
    }
}