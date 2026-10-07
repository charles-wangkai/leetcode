import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<String> removeInvalidParentheses(String s) {
    int[] indices =
        IntStream.range(0, s.length()).filter(i -> !Character.isLetter(s.charAt(i))).toArray();

    int[] validMasks =
        IntStream.range(0, 1 << indices.length).filter(mask -> isValid(s, indices, mask)).toArray();
    int maxBitCount = Arrays.stream(validMasks).map(Integer::bitCount).max().getAsInt();

    return Arrays.stream(validMasks)
        .filter(mask -> Integer.bitCount(mask) == maxBitCount)
        .mapToObj(mask -> buildString(s, indices, mask))
        .distinct()
        .toList();
  }

  boolean isValid(String s, int[] indices, int mask) {
    int depth = 0;
    for (int i = 0; i < indices.length; ++i) {
      if (((mask >> i) & 1) == 1) {
        depth += (s.charAt(indices[i]) == '(') ? 1 : -1;
        if (depth == -1) {
          return false;
        }
      }
    }

    return depth == 0;
  }

  String buildString(String s, int[] indices, int mask) {
    Set<Integer> removed =
        IntStream.range(0, indices.length)
            .filter(i -> ((mask >> i) & 1) == 0)
            .map(i -> indices[i])
            .boxed()
            .collect(Collectors.toSet());

    return IntStream.range(0, s.length())
        .filter(i -> !removed.contains(i))
        .mapToObj(s::charAt)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
