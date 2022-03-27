import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

    return List.of(
        set1.stream().filter(x -> !set2.contains(x)).collect(Collectors.toList()),
        set2.stream().filter(x -> !set1.contains(x)).collect(Collectors.toList()));
  }
}