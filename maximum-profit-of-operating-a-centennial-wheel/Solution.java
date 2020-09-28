class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int maxProfit = 0;
        int profit = 0;
        int waitNum = 0;
        int result = -1;
        for (int i = 0; i < customers.length || waitNum != 0; ++i) {
            if (i < customers.length) {
                waitNum += customers[i];
            }
            int boardNum = Math.min(4, waitNum);
            profit += boardNum * boardingCost - runningCost;
            waitNum -= boardNum;
            if (profit > maxProfit) {
                maxProfit = profit;
                result = i + 1;
            }
        }

        return result;
    }
}