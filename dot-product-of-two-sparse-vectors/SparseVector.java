import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SparseVector {
  Map<Integer, Integer> indexToValue;

  SparseVector(int[] nums) {
    indexToValue =
        IntStream.range(0, nums.length)
            .filter(i -> nums[i] != 0)
            .boxed()
            .collect(Collectors.toMap(i -> i, i -> nums[i]));
  }

  // Return the dotProduct of two sparse vectors
  public int dotProduct(SparseVector vec) {
    return indexToValue.keySet().stream()
        .mapToInt(index -> indexToValue.get(index) * vec.indexToValue.getOrDefault(index, 0))
        .sum();
  }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
