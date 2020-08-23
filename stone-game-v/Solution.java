import java.util.Arrays;

class Solution {
    int[][] cache;

    public int stoneGameV(int[] stoneValue) {
        cache = new int[stoneValue.length][stoneValue.length];
        for (int i = 0; i < cache.length; ++i) {
            Arrays.fill(cache[i], -1);
        }

        return search(stoneValue, 0, stoneValue.length - 1);
    }

    int search(int[] stoneValue, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return 0;
        }

        if (cache[beginIndex][endIndex] == -1) {
            int total = 0;
            for (int i = beginIndex; i <= endIndex; ++i) {
                total += stoneValue[i];
            }

            int result = 0;
            int leftSum = 0;
            for (int i = beginIndex; i < endIndex; ++i) {
                leftSum += stoneValue[i];
                int rightSum = total - leftSum;

                if (leftSum < rightSum) {
                    result = Math.max(result, leftSum + search(stoneValue, beginIndex, i));
                } else if (leftSum > rightSum) {
                    result = Math.max(result, rightSum + search(stoneValue, i + 1, endIndex));
                } else {
                    result = Math.max(result, Math.max(leftSum + search(stoneValue, beginIndex, i),
                            rightSum + search(stoneValue, i + 1, endIndex)));
                }
            }

            cache[beginIndex][endIndex] = result;
        }

        return cache[beginIndex][endIndex];
    }
}