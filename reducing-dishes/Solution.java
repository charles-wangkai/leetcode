import java.util.Arrays;

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int result = 0;
        int suffixSum = 0;
        int total = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            total += suffixSum + satisfaction[i];
            result = Math.max(result, total);

            suffixSum += satisfaction[i];
        }

        return result;
    }
}