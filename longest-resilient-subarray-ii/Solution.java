import java.math.BigInteger;

class Solution {
  public int resilientSubarray(int[] nums, int k) {
    ModInt modInt = new ModInt(k);

    int result = -1;
    int sum = -1;
    int zeroSumFirstIndex = -1;
    for (int i = 0; i < nums.length; ++i) {
      if (i == 0 || modInt.mod(nums[i]) != modInt.mod(nums[i - 1])) {
        sum = 0;
        zeroSumFirstIndex = i - 1;
      }

      sum = modInt.addMod(sum, nums[i]);

      if (sum == modInt.mod(nums[i])) {
        result = Math.max(result, i - zeroSumFirstIndex);
      }
    }

    return result;
  }
}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
