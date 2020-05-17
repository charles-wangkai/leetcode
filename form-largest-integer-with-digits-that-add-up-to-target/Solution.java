import java.util.Arrays;

class Solution {
    public String largestNumber(int[] cost, int target) {
        int[][] totalCostToDigitCounts = new int[target + 1][];
        totalCostToDigitCounts[0] = new int[9];
        for (int i = 0; i < target; ++i) {
            if (totalCostToDigitCounts[i] != null) {
                for (int j = 0; j < cost.length; ++j) {
                    int nextTotalCost = i + cost[j];
                    if (nextTotalCost <= target) {
                        int[] nextDigitCounts = Arrays.copyOf(totalCostToDigitCounts[i],
                                totalCostToDigitCounts[i].length);
                        ++nextDigitCounts[j];

                        if (isLarger(nextDigitCounts, totalCostToDigitCounts[nextTotalCost])) {
                            totalCostToDigitCounts[nextTotalCost] = nextDigitCounts;
                        }
                    }
                }
            }
        }

        return convertToNumber(totalCostToDigitCounts[target]);
    }

    static boolean isLarger(int[] digitCounts1, int[] digitCounts2) {
        if (digitCounts2 == null) {
            return true;
        }

        int countSum1 = Arrays.stream(digitCounts1).sum();
        int countSum2 = Arrays.stream(digitCounts2).sum();
        if (countSum1 != countSum2) {
            return countSum1 > countSum2;
        }

        for (int i = 8; i >= 0; --i) {
            if (digitCounts1[i] != digitCounts2[i]) {
                return digitCounts1[i] > digitCounts2[i];
            }
        }

        return false;
    }

    String convertToNumber(int[] digitCounts) {
        if (digitCounts == null) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (int i = digitCounts.length - 1; i >= 0; --i) {
            for (int j = 0; j < digitCounts[i]; ++j) {
                result.append(i + 1);
            }
        }

        return result.toString();
    }
}