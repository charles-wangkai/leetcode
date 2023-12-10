import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int[] findIntersectionValues(int[] nums1, int[] nums2) {
    Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

    return new int[] {
      (int) Arrays.stream(nums1).filter(set2::contains).count(),
      (int) Arrays.stream(nums2).filter(set1::contains).count()
    };
  }
}