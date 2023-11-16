// Definition for BigArray.
abstract class BigArray {
  BigArray(int[] elements) {}

  public abstract int at(long index);

  public abstract long size();
}

class Solution {
  public int countBlocks(BigArray nums) {
    int result = 0;
    long beginIndex = 0;
    while (beginIndex != nums.size()) {
      beginIndex = findEndIndex(nums, beginIndex) + 1;
      ++result;
    }

    return result;
  }

  long findEndIndex(BigArray nums, long beginIndex) {
    long result = -1;
    long lower = beginIndex;
    long upper = nums.size() - 1;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (nums.at(middle) == nums.at(beginIndex)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
