import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        Map<Integer, Integer> leftSumToIndex = new HashMap<>();
        leftSumToIndex.put(0, -1);
        int leftSum = 0;
        int leftMinLength = Integer.MAX_VALUE;
        int[] leftMinLengths = new int[n];
        for (int i = 0; i < n; ++i) {
            leftSum += arr[i];

            if (leftSumToIndex.containsKey(leftSum - target)) {
                leftMinLength = Math.min(leftMinLength, i - leftSumToIndex.get(leftSum - target));
            }

            leftMinLengths[i] = leftMinLength;

            leftSumToIndex.put(leftSum, i);
        }

        Map<Integer, Integer> rightSumToIndex = new HashMap<>();
        rightSumToIndex.put(0, n);
        int rightSum = 0;
        int rightMinLength = Integer.MAX_VALUE;
        int[] rightMinLengths = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            rightSum += arr[i];

            if (rightSumToIndex.containsKey(rightSum - target)) {
                rightMinLength = Math.min(rightMinLength, rightSumToIndex.get(rightSum - target) - i);
            }

            rightMinLengths[i] = rightMinLength;

            rightSumToIndex.put(rightSum, i);
        }

        int result = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (leftMinLengths[i] != Integer.MAX_VALUE && rightMinLengths[i + 1] != Integer.MAX_VALUE
                    && (result == -1 || leftMinLengths[i] + rightMinLengths[i + 1] < result)) {
                result = leftMinLengths[i] + rightMinLengths[i + 1];
            }
        }

        return result;
    }
}