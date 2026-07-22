import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean[] transformStr(String s, String[] strs) {
    int[] oneIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '1').toArray();

    boolean[] result = new boolean[strs.length];
    for (int i = 0; i < result.length; ++i) {
      String str = strs[i];

      List<Integer> targetOneIndices =
          IntStream.range(0, str.length())
              .filter(j -> str.charAt(j) == '1')
              .boxed()
              .collect(Collectors.toList());
      int[] wildcardIndices =
          IntStream.range(0, str.length()).filter(j -> str.charAt(j) == '?').toArray();
      if (targetOneIndices.size() <= oneIndices.length
          && targetOneIndices.size() + wildcardIndices.length >= oneIndices.length) {
        for (int j = oneIndices.length - targetOneIndices.size() - 1; j >= 0; --j) {
          targetOneIndices.add(wildcardIndices[wildcardIndices.length - 1 - j]);
        }
        Collections.sort(targetOneIndices);

        result[i] =
            IntStream.range(0, oneIndices.length)
                .allMatch(j -> oneIndices[j] <= targetOneIndices.get(j));
      }
    }

    return result;
  }
}