class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        int[][] maxProducts = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; ++i) {
            for (int j = 0; j <= length2; ++j) {
                if (i == 0 || j == 0) {
                    maxProducts[i][j] = Integer.MIN_VALUE;
                } else {
                    maxProducts[i][j] = Math.max(Math.max(maxProducts[i - 1][j], maxProducts[i][j - 1]),
                            Math.max(0, maxProducts[i - 1][j - 1]) + nums1[i - 1] * nums2[j - 1]);
                }
            }
        }

        return maxProducts[length1][length2];
    }
}