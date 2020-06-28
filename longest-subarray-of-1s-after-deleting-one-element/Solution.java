import java.util.stream.IntStream;

class Solution {
    public int longestSubarray(int[] nums) {
        int[] leftOneNums = new int[nums.length];
        int leftOneNum = 0;
        for (int i = 0; i < leftOneNums.length; ++i) {
            leftOneNums[i] = leftOneNum;

            if (nums[i] == 1) {
                ++leftOneNum;
            } else {
                leftOneNum = 0;
            }
        }

        int[] rightOneNums = new int[nums.length];
        int rightOneNum = 0;
        for (int i = rightOneNums.length - 1; i >= 0; --i) {
            rightOneNums[i] = rightOneNum;

            if (nums[i] == 1) {
                ++rightOneNum;
            } else {
                rightOneNum = 0;
            }
        }

        return IntStream.range(0, nums.length).map(i -> leftOneNums[i] + rightOneNums[i]).max().getAsInt();
    }
}