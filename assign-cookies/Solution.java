import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public int findContentChildren(int[] g, int[] s) {
    List<Integer> sizes = new ArrayList<>();
    for (int size : s) {
      sizes.add(size);
    }

    Collections.sort(sizes);

    int contentNum = 0;
    for (int greed : g) {
      int index = Collections.binarySearch(sizes, greed);
      if (index < 0) {
        index = -1 - index;
      }

      if (index < sizes.size()) {
        sizes.remove(index);
        contentNum++;
      }
    }
    return contentNum;
  }
}
