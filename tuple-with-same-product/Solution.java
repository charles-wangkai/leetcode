import java.util.HashMap;
import java.util.Map;

class Solution {
  public int tupleSameProduct(int[] nums) {
    Map<Integer, Integer> productToCount = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        int product = nums[i] * nums[j];
        productToCount.put(product, productToCount.getOrDefault(product, 0) + 1);
      }
    }

    return productToCount.values().stream().mapToInt(count -> count * (count - 1) * 4).sum();
  }
}
