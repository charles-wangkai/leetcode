public class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int result = 0;
        int beginIndex = 0;
        int sum = 0;
        for (int endIndex = 0; endIndex < arr.length; ++endIndex) {
            sum += arr[endIndex];
            if (endIndex - beginIndex == k) {
                sum -= arr[beginIndex];
                ++beginIndex;
            }

            if (endIndex - beginIndex + 1 == k && sum >= threshold * k) {
                ++result;
            }
        }

        return result;
    }
}