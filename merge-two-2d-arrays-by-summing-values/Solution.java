import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
    List<int[]> merged = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    while (index1 != nums1.length || index2 != nums2.length) {
      if (index2 == nums2.length
          || (index1 != nums1.length && nums1[index1][0] <= nums2[index2][0])) {
        merge(merged, nums1[index1]);
        ++index1;
      } else {
        merge(merged, nums2[index2]);
        ++index2;
      }
    }

    return merged.stream().toArray(int[][]::new);
  }

  void merge(List<int[]> merged, int[] element) {
    if (!merged.isEmpty() && merged.getLast()[0] == element[0]) {
      merged.getLast()[1] += element[1];
    } else {
      merged.add(element);
    }
  }
}
