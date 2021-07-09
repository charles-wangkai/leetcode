import java.util.ArrayList;
import java.util.List;

class Solution {
  public int lengthOfLIS(int[] nums) {
    List<Integer> subsequence = new ArrayList<>();
    for (int num : nums) {
      if (subsequence.isEmpty() || num > subsequence.get(subsequence.size() - 1)) {
        subsequence.add(num);
      } else {
        subsequence.set(findFirstGreaterEqualIndex(subsequence, num), num);
      }
    }

    return subsequence.size();
  }

  int findFirstGreaterEqualIndex(List<Integer> subsequence, int target) {
    int result = -1;
    int lower = 0;
    int upper = subsequence.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (subsequence.get(middle) >= target) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}
