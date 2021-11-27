import java.util.stream.IntStream;

class Solution {
  public int[] productExceptSelf(int[] nums) {
    int[] result = IntStream.range(0, nums.length).map(i -> 1).toArray();

    int rightProduct = 1;
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] *= rightProduct;
      rightProduct *= nums[i];
    }

    int leftProduct = 1;
    for (int i = 0; i < result.length; ++i) {
      result[i] *= leftProduct;
      leftProduct *= nums[i];
    }

    return result;
  }
}
