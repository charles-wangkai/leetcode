import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int minMutation(String start, String end, String[] bank) {
    int minDistance = search(end, bank, new HashSet<>(), start);

    return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
  }

  int search(String end, String[] bank, Set<String> seen, String current) {
    if (current.equals(end)) {
      return seen.size();
    }

    int result = Integer.MAX_VALUE;
    for (String next : bank) {
      if (!seen.contains(next) && canMutate(current, next)) {
        seen.add(next);
        result = Math.min(result, search(end, bank, seen, next));
        seen.remove(next);
      }
    }

    return result;
  }

  boolean canMutate(String gene1, String gene2) {
    return IntStream.range(0, gene1.length())
            .filter(i -> gene1.charAt(i) != gene2.charAt(i))
            .count()
        == 1;
  }
}
