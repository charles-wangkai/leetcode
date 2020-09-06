class Solution {
    public int minCost(String s, int[] cost) {
        int result = 0;
        int costSum = 0;
        int maxCost = 0;
        for (int i = 0; i <= s.length(); ++i) {
            if (i != s.length() && (i == 0 || s.charAt(i) == s.charAt(i - 1))) {
                costSum += cost[i];
                maxCost = Math.max(maxCost, cost[i]);
            } else {
                result += costSum - maxCost;

                if (i != s.length()) {
                    costSum = cost[i];
                    maxCost = cost[i];
                }
            }
        }

        return result;
    }
}