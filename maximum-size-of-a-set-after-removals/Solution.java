import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int maximumSetSize(int[] nums1, int[] nums2) {
    int n = nums1.length;

    Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
    int onlyNum1 = (int) set1.stream().filter(x -> !set2.contains(x)).count();
    int commonNum = set1.size() - onlyNum1;
    int onlyNum2 = set2.size() - commonNum;

    int onlyInSet1 = Math.min(onlyNum1, n / 2);
    int onlyInSet2 = Math.min(onlyNum2, n / 2);
    int rest1 = n / 2 - onlyInSet1;
    int rest2 = n / 2 - onlyInSet2;

    return onlyInSet1 + onlyInSet2 + Math.min(rest1 + rest2, commonNum);
  }
}