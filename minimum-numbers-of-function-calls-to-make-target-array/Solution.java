class Solution {
    public int minOperations(int[] nums) {
        int increaseCount = 0;
        int doubleCount = 0;
        for (int num : nums) {
            String s = Integer.toBinaryString(num);

            increaseCount += s.chars().filter(ch -> ch == '1').count();
            doubleCount = Math.max(doubleCount, s.length() - 1);
        }

        return increaseCount + doubleCount;
    }
}