import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int kIncreasing(int[] arr, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] subsequences = new List[k];
    for (int i = 0; i < subsequences.length; ++i) {
      subsequences[i] = new ArrayList<>();
    }

    for (int i = 0; i < arr.length; ++i) {
      List<Integer> subsequence = subsequences[i % k];

      if (subsequence.isEmpty() || arr[i] >= subsequence.get(subsequence.size() - 1)) {
        subsequence.add(arr[i]);
      } else {
        subsequence.set(findFirstGreaterIndex(subsequence, arr[i]), arr[i]);
      }
    }

    return arr.length - Arrays.stream(subsequences).mapToInt(List::size).sum();
  }

  int findFirstGreaterIndex(List<Integer> subsequence, int target) {
    int result = -1;
    int lower = 0;
    int upper = subsequence.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (subsequence.get(middle) > target) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}