import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int maxPotholes(String road, int budget) {
    List<Integer> lengths = new ArrayList<>();
    int length = 0;
    for (int i = 0; i <= road.length(); ++i) {
      if (i != road.length() && road.charAt(i) == 'x') {
        ++length;
      } else {
        if (length != 0) {
          lengths.add(length);
        }

        length = 0;
      }
    }
    Collections.sort(lengths, Comparator.reverseOrder());

    int result = 0;
    for (int l : lengths) {
      if (budget >= 2) {
        int current = Math.min(l, budget - 1);
        result += current;
        budget -= current + 1;
      }
    }

    return result;
  }
}