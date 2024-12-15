class Solution {
  static final int BASE = 31;
  static final int MODULUS = 1_000_000_007;

  public int beautifulSplits(int[] nums) {
    int[] basePowers = new int[nums.length + 1];
    basePowers[0] = 1;
    for (int i = 1; i < basePowers.length; ++i) {
      basePowers[i] = multiplyMod(basePowers[i - 1], BASE);
    }

    int[] prefixHashes = new int[nums.length + 1];
    for (int i = 1; i < prefixHashes.length; ++i) {
      prefixHashes[i] = addMod(multiplyMod(prefixHashes[i - 1], BASE), nums[i - 1]);
    }

    int result = 0;
    for (int length1 = 1; length1 < nums.length; ++length1) {
      for (int length2 = 1; length1 + length2 < nums.length; ++length2) {
        int length3 = nums.length - length1 - length2;
        if ((length1 <= length2
                && computeRangeHash(basePowers, prefixHashes, 0, length1 - 1)
                    == computeRangeHash(basePowers, prefixHashes, length1, length1 + length1 - 1))
            || (length2 <= length3
                && computeRangeHash(basePowers, prefixHashes, length1, length1 + length2 - 1)
                    == computeRangeHash(
                        basePowers,
                        prefixHashes,
                        length1 + length2,
                        length1 + length2 + length2 - 1))) {
          ++result;
        }
      }
    }

    return result;
  }

  int computeRangeHash(int[] basePowers, int[] prefixHashes, int beginIndex, int endIndex) {
    return addMod(
        prefixHashes[endIndex + 1],
        -multiplyMod(prefixHashes[beginIndex], basePowers[endIndex - beginIndex + 1]));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}