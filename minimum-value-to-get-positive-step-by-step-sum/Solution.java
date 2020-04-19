class Solution {
    public int minStartValue(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            minSum = Math.min(minSum, sum);
        }

        return Math.max(1, 1 - minSum);
    }
}