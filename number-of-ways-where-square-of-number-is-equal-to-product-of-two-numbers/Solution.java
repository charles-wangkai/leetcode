import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        return computeTripletNum(nums1, nums2) + computeTripletNum(nums2, nums1);
    }

    int computeTripletNum(int[] values1, int[] values2) {
        Map<Long, Integer> squareToCount = new HashMap<>();
        for (int value : values1) {
            long square = (long) value * value;
            squareToCount.put(square, squareToCount.getOrDefault(square, 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < values2.length; ++i) {
            for (int j = i + 1; j < values2.length; ++j) {
                result += squareToCount.getOrDefault((long) values2[i] * values2[j], 0);
            }
        }

        return result;
    }
}