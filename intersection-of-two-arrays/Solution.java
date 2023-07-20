import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> numSet1 = buildSet(nums1);
    Set<Integer> numSet2 = buildSet(nums2);

    List<Integer> merged = new ArrayList<>();
    for (int num1 : numSet1) {
      if (numSet2.contains(num1)) {
        merged.add(num1);
      }
    }

    int[] result = new int[merged.size()];
    for (int i = 0; i < merged.size(); i++) {
      result[i] = merged.get(i);
    }
    return result;
  }

  Set<Integer> buildSet(int[] nums) {
    return Arrays.stream(nums).collect(HashSet::new, Set::add, Set::addAll);
  }
}
