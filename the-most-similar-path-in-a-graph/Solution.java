import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] adjLists = new List[n];
        for (int i = 0; i < adjLists.length; ++i) {
            adjLists[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            adjLists[road[0]].add(road[1]);
            adjLists[road[1]].add(road[0]);
        }

        int[][] distances = new int[targetPath.length][n];
        for (int i = 0; i < distances.length; ++i) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; ++i) {
            distances[0][i] = names[i].equals(targetPath[0]) ? 0 : 1;
        }

        for (int i = 0; i < targetPath.length - 1; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int adj : adjLists[j]) {
                    distances[i + 1][adj] = Math.min(distances[i + 1][adj],
                            distances[i][j] + (names[adj].equals(targetPath[i + 1]) ? 0 : 1));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int minDistance = Arrays.stream(distances[targetPath.length - 1]).min().getAsInt();
        int current = IntStream.range(0, n).filter(i -> distances[targetPath.length - 1][i] == minDistance).findAny()
                .getAsInt();
        result.add(current);

        for (int i = targetPath.length - 2; i >= 0; --i) {
            boolean found = false;
            for (int j = 0; j < n; ++j) {
                for (int adj : adjLists[j]) {
                    if (adj == current && distances[i][j]
                            + (names[adj].equals(targetPath[i + 1]) ? 0 : 1) == distances[i + 1][current]) {
                        current = j;
                        result.add(current);
                        found = true;

                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }

        Collections.reverse(result);

        return result;
    }
}
