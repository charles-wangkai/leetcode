import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public int[] largestPower(int[] nums) {
    List<Integer> perm = Arrays.stream(nums).boxed().collect(Collectors.toList());

    int[] result = new int[15];
    for (int i = 0; i < result.length; ++i) {
      perm.sort(Comparator.reverseOrder());

      int b = 14 - i;

      for (int j = 0; j < perm.size() && ((perm.get(j) >> b) & 1) == 1; ++j) {
        ++result[i];
      }

      for (int j = result[i]; j < perm.size(); ++j) {
        perm.set(j, perm.get(j) & ~(1 << b));
      }
    }

    return result;
  }
}