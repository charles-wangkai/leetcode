import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

    return Arrays.stream(nums2).filter(set1::contains).distinct().toArray();
  }
}
