import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public int[] assignElements(int[] groups, int[] elements) {
    int maxGroup = Arrays.stream(groups).max().getAsInt();

    Map<Integer, List<Integer>> groupToIndices = new HashMap<>();
    for (int i = 0; i < groups.length; ++i) {
      groupToIndices.putIfAbsent(groups[i], new ArrayList<>());
      groupToIndices.get(groups[i]).add(i);
    }

    int[] result = new int[groups.length];
    Arrays.fill(result, -1);

    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < elements.length; ++i) {
      if (!seen.contains(elements[i])) {
        seen.add(elements[i]);

        for (int group = elements[i]; group <= maxGroup; group += elements[i]) {
          if (groupToIndices.containsKey(group)) {
            for (int index : groupToIndices.get(group)) {
              result[index] = i;
            }

            groupToIndices.remove(group);
          }
        }
      }
    }

    return result;
  }
}