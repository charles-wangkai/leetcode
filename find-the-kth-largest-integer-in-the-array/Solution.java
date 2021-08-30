import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public String kthLargestNumber(String[] nums, int k) {
    return Arrays.stream(nums)
        .map(BigInteger::new)
        .sorted(Comparator.reverseOrder())
        .skip(k - 1)
        .findFirst()
        .get()
        .toString();
  }
}
