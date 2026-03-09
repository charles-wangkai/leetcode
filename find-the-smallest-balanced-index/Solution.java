import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  public int smallestBalancedIndex(int[] nums) {
    long[] leftSums = new long[nums.length];
    long leftSum = 0;
    for (int i = 0; i < leftSums.length; ++i) {
      leftSums[i] = leftSum;
      leftSum += nums[i];
    }

    long[] rightProducts = new long[nums.length];
    long rightProduct = 1;
    for (int i = rightProducts.length - 1; i >= 0; --i) {
      rightProducts[i] = rightProduct;

      if (rightProduct != -1) {
        BigInteger product = BigInteger.valueOf(rightProduct).multiply(BigInteger.valueOf(nums[i]));
        if (product.compareTo(BigInteger.valueOf(leftSums[leftSums.length - 1])) > 0) {
          rightProduct = -1;
        } else {
          rightProduct = product.longValue();
        }
      }
    }

    return IntStream.range(0, nums.length)
        .filter(i -> leftSums[i] == rightProducts[i])
        .min()
        .orElse(-1);
  }
}