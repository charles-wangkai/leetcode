import java.util.Arrays;

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;

        int[] trailingZeroNums = Arrays.stream(grid).mapToInt(this::computeTrailingZeroNum).toArray();

        int result = 0;
        for (int i = 0; i < n; ++i) {
            int lowerTrailingZeroNum = n - 1 - i;

            int goodIndex = i;
            while (goodIndex != n && trailingZeroNums[goodIndex] < lowerTrailingZeroNum) {
                ++goodIndex;
            }
            if (goodIndex == n) {
                return -1;
            }

            result += goodIndex - i;

            int temp = trailingZeroNums[goodIndex];
            for (int j = goodIndex; j >= i + 1; --j) {
                trailingZeroNums[j] = trailingZeroNums[j - 1];
            }
            trailingZeroNums[i] = temp;
        }

        return result;
    }

    int computeTrailingZeroNum(int[] row) {
        int result = 0;
        for (int i = row.length - 1; i >= 0; --i) {
            if (row[i] != 0) {
                break;
            }

            ++result;
        }

        return result;
    }
}