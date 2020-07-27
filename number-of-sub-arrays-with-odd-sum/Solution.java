class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {
        int evenPrefixSumCount = 1;
        int oddPrefixSumCount = 0;
        int prefixSum = 0;
        int result = 0;
        for (int x : arr) {
            prefixSum += x;
            if (prefixSum % 2 == 0) {
                result = addMod(result, oddPrefixSumCount);
                ++evenPrefixSumCount;
            } else {
                result = addMod(result, evenPrefixSumCount);
                ++oddPrefixSumCount;
            }
        }

        return result;
    }

    static int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}