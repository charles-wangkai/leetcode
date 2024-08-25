import java.util.stream.IntStream;

class Solution {
  public int countPairs(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (isAlmostEqual(nums[i], nums[j])) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isAlmostEqual(int x, int y) {
    if (x < y) {
      return isAlmostEqual(y, x);
    }

    String sx = String.valueOf(x);
    String sy = String.valueOf(y);
    while (sy.length() != sx.length()) {
      sy = "0" + sy;
    }

    String sy_ = sy;
    int[] diffIndices =
        IntStream.range(0, sx.length()).filter(i -> sx.charAt(i) != sy_.charAt(i)).toArray();

    return diffIndices.length == 0
        || (diffIndices.length == 2
            && sx.charAt(diffIndices[0]) == sy.charAt(diffIndices[1])
            && sx.charAt(diffIndices[1]) == sy.charAt(diffIndices[0]));
  }
}