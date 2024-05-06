// https://leetcode.com/problems/minimum-cost-to-equalize-array/solutions/5114202/java-c-python-4-cases-o-n-solution/

import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
    int min = Arrays.stream(nums).min().getAsInt();
    int max = Arrays.stream(nums).max().getAsInt();
    long total = (long) max * nums.length - Arrays.stream(nums).asLongStream().sum();

    if (cost2 >= cost1 * 2 || nums.length <= 2) {
      return mod(total * cost1);
    }

    int op1 = (int) Math.max(0, (max - min) * 2 - total);
    long op2 = total - op1;
    long minCost = (op1 + op2 % 2) * cost1 + op2 / 2 * cost2;

    total += op1 / (nums.length - 2) * nums.length;
    op1 %= nums.length - 2;
    op2 = total - op1;
    minCost = Math.min(minCost, (op1 + op2 % 2) * cost1 + op2 / 2 * cost2);

    for (int i = 0; i <= 1; ++i) {
      total += nums.length;
      minCost = Math.min(minCost, total % 2 * cost1 + total / 2 * cost2);
    }

    return mod(minCost);
  }

  int mod(long x) {
    return (int) (x % MODULUS);
  }
}