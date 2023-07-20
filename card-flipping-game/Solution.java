import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Solution {
  public int flipgame(int[] fronts, int[] backs) {
    Set<Integer> impossibles = new HashSet<>();
    SortedSet<Integer> numbers = new TreeSet<>();
    for (int i = 0; i < fronts.length; i++) {
      numbers.add(fronts[i]);
      numbers.add(backs[i]);

      if (fronts[i] == backs[i]) {
        impossibles.add(fronts[i]);
      }
    }

    for (int number : numbers) {
      if (!impossibles.contains(number)) {
        return number;
      }
    }
    return 0;
  }
}
