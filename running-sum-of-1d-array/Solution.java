class Solution {
    public int[] runningSum(int[] nums) {
        int sum = 0;
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; ++i) {
            sum += nums[i];
            result[i] = sum;
        }

        return result;
    }
}