import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minProductSum(int[] nums1, int[] nums2) {
    int[] sorted1 = Arrays.stream(nums1).boxed().sorted().mapToInt(x -> x).toArray();
    int[] sorted2 =
        Arrays.stream(nums2).boxed().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();

    return IntStream.range(0, sorted1.length).map(i -> sorted1[i] * sorted2[i]).sum();
  }
}