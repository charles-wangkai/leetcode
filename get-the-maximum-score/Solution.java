class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        long sum1 = 0;
        long sum2 = 0;

        int index1 = 0;
        int index2 = 0;
        while (index1 != nums1.length || index2 != nums2.length) {
            int value1 = (index1 == nums1.length) ? Integer.MAX_VALUE : nums1[index1];
            int value2 = (index2 == nums2.length) ? Integer.MAX_VALUE : nums2[index2];

            if (value1 < value2) {
                sum1 += value1;
                ++index1;
            } else if (value1 > value2) {
                sum2 += value2;
                ++index2;
            } else {
                long nextSum = Math.max(sum1, sum2) + value1;

                sum1 = nextSum;
                sum2 = nextSum;
                ++index1;
                ++index2;
            }
        }

        return (int) (Math.max(sum1, sum2) % 1_000_000_007);
    }
}