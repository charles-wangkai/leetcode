class Solution {
    public int getMaxLen(int[] nums) {
        int result = 0;
        int beginIndex = 0;
        int negCount = 0;
        int firstNegIndex = -1;
        int lastNegIndex = -1;
        for (int i = 0; i <= nums.length; ++i) {
            if (i == nums.length || nums[i] == 0) {
                if (negCount % 2 == 0) {
                    result = Math.max(result, i - beginIndex);
                } else {
                    result = Math.max(result, Math.max(i - firstNegIndex - 1, lastNegIndex - beginIndex));
                }

                beginIndex = i + 1;
                negCount = 0;
                firstNegIndex = -1;
                lastNegIndex = -1;
            } else if (nums[i] < 0) {
                ++negCount;
                if (firstNegIndex == -1) {
                    firstNegIndex = i;
                }
                lastNegIndex = i;
            }
        }

        return result;
    }
}