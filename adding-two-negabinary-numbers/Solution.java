import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public int[] addNegabinary(int[] arr1, int[] arr2) {
    List<Integer> sums = new ArrayList<>();
    for (int i1 = arr1.length - 1, i2 = arr2.length - 1; i1 >= 0 || i2 >= 0; i1--, i2--) {
      sums.add((i1 >= 0 ? arr1[i1] : 0) + (i2 >= 0 ? arr2[i2] : 0));
    }

    int index = 0;
    while (index < sums.size()) {
      if (sums.get(index) >= 2) {
        sums.set(index, sums.get(index) - 2);

        if (index + 1 < sums.size() && sums.get(index + 1) >= 1) {
          sums.set(index + 1, sums.get(index + 1) - 1);
        } else {
          if (index + 1 == sums.size()) {
            sums.add(1);
          } else {
            sums.set(index + 1, sums.get(index + 1) + 1);
          }

          if (index + 2 == sums.size()) {
            sums.add(1);
          } else {
            sums.set(index + 2, sums.get(index + 2) + 1);
          }
        }
      } else {
        index++;
      }
    }

    while (!sums.isEmpty() && sums.get(sums.size() - 1) == 0) {
      sums.remove(sums.size() - 1);
    }

    if (sums.isEmpty()) {
      return new int[] {0};
    }

    Collections.reverse(sums);
    return sums.stream().mapToInt(x -> x).toArray();
  }
}
