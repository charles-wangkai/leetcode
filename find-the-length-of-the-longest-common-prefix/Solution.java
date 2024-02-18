import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    Set<String> prefixs1 =
        Arrays.stream(arr1)
            .boxed()
            .flatMap(
                x -> {
                  String s = String.valueOf(x);

                  return IntStream.range(0, s.length()).mapToObj(i -> s.substring(0, i + 1));
                })
            .collect(Collectors.toSet());

    int result = 0;
    for (int x : arr2) {
      String s = String.valueOf(x);
      for (int i = 0; i < s.length() && prefixs1.contains(s.substring(0, i + 1)); ++i) {
        result = Math.max(result, i + 1);
      }
    }

    return result;
  }
}