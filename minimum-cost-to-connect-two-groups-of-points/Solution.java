import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    int[][] cache;

    public int connectTwoGroups(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();

        int[] minFirstCosts = new int[size2];
        for (int j = 0; j < minFirstCosts.length; ++j) {
            int j_ = j;
            minFirstCosts[j] = IntStream.range(0, size1).map(i -> cost.get(i).get(j_)).min().getAsInt();
        }

        cache = new int[size1 + 1][1 << size2];
        for (int i = 0; i < cache.length; ++i) {
            Arrays.fill(cache[i], -1);
        }

        return search(cost, minFirstCosts, 0, 0);
    }

    int search(List<List<Integer>> cost, int[] minFirstCosts, int index, int mask) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();

        if (cache[index][mask] != -1) {
            return cache[index][mask];
        }

        int result;
        if (index == size1) {
            result = 0;
            for (int j = 0; j < size2; ++j) {
                if ((mask & (1 << j)) == 0) {
                    result += minFirstCosts[j];
                }
            }
        } else {
            result = Integer.MAX_VALUE;
            for (int j = 0; j < size2; ++j) {
                result = Math.min(result,
                        cost.get(index).get(j) + search(cost, minFirstCosts, index + 1, mask | (1 << j)));
            }
        }

        cache[index][mask] = result;

        return result;
    }
}