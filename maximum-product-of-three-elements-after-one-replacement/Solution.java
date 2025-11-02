import java.util.Arrays;

class Solution {
  public long maxProduct(int[] nums) {
    int[] absSorted = Arrays.stream(nums).map(Math::abs).sorted().toArray();

    return (long) absSorted[absSorted.length - 1] * absSorted[absSorted.length - 2] * 100000;
  }
}