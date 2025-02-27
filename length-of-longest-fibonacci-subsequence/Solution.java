import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int lenLongestFibSubseq(int[] arr) {
    Set<Integer> values = Arrays.stream(arr).boxed().collect(Collectors.toSet());

    int result = 0;
    for (int i = 0; i < arr.length; ++i) {
      for (int j = i + 1; j < arr.length; ++j) {
        result = Math.max(result, computeLength(values, arr[i], arr[j]));
      }
    }

    return result;
  }

  int computeLength(Set<Integer> values, int prev, int curr) {
    int length = 2;
    while (true) {
      int next = prev + curr;
      if (!values.contains(next)) {
        break;
      }

      ++length;
      prev = curr;
      curr = next;
    }

    return (length >= 3) ? length : 0;
  }
}
