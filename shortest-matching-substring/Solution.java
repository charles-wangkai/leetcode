import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int shortestMatchingSubstring(String s, String p) {
    String[] parts =
        Arrays.stream(p.split("\\*")).filter(part -> !part.isEmpty()).toArray(String[]::new);
    if (parts.length == 0) {
      return 0;
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] beginIndexLists =
        Arrays.stream(parts).map(part -> findBeginIndices(s, part)).toArray(List[]::new);

    int[] positions = new int[beginIndexLists.length];

    int result = Integer.MAX_VALUE;
    while (positions[0] != beginIndexLists[0].size()) {
      result = Math.min(result, search(parts, beginIndexLists, positions, 0));

      ++positions[0];
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int search(String[] parts, List<Integer>[] beginIndexLists, int[] positions, int depth) {
    if (depth == parts.length) {
      return beginIndexLists[depth - 1].get(positions[depth - 1])
          + parts[depth - 1].length()
          - beginIndexLists[0].get(positions[0]);
    }

    while (depth != 0
        && positions[depth] != beginIndexLists[depth].size()
        && beginIndexLists[depth].get(positions[depth])
            < beginIndexLists[depth - 1].get(positions[depth - 1]) + parts[depth - 1].length()) {
      ++positions[depth];
    }
    if (positions[depth] == beginIndexLists[depth].size()) {
      return Integer.MAX_VALUE;
    }

    return search(parts, beginIndexLists, positions, depth + 1);
  }

  List<Integer> findBeginIndices(String s, String part) {
    return IntStream.range(0, s.length()).filter(i -> s.startsWith(part, i)).boxed().toList();
  }
}