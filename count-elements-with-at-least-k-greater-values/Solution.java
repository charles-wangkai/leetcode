import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int countElements(int[] nums, int k) {
    if (k == 0) {
      return nums.length;
    }

    int threshold =
        Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).skip(k - 1).findFirst().get();

    return (int) Arrays.stream(nums).filter(x -> x < threshold).count();
  }
}