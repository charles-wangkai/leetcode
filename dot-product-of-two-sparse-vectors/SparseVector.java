import java.util.HashMap;
import java.util.Map;

class SparseVector {
    Map<Integer, Integer> indexToValue = new HashMap<>();

    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                indexToValue.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        for (int index : indexToValue.keySet()) {
            if (vec.indexToValue.containsKey(index)) {
                result += indexToValue.get(index) * vec.indexToValue.get(index);
            }
        }

        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);