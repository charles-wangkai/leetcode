import java.math.BigInteger;

class Solution {
  public int minimumCost(int[] nums, int k) {
    long operationNum = 0;
    int rest = k;
    for (int num : nums) {
      int oper = Math.ceilDiv(Math.max(0, num - rest), k);

      operationNum += oper;
      rest += oper * k - num;
    }

    return BigInteger.valueOf(operationNum)
        .multiply(BigInteger.valueOf(operationNum + 1))
        .divide(BigInteger.TWO)
        .mod(BigInteger.valueOf(1_000_000_007))
        .intValue();
  }
}