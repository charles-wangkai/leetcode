import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  static final int LIMIT = 100000;

  public int[] smallestRange(List<List<Integer>> nums) {
    int lower = -LIMIT - 1;
    int upper = LIMIT + 1;

    SortedSet<Element> elements =
        new TreeSet<>(
            Comparator.<Element, Integer>comparing(
                    element -> nums.get(element.listIndex()).get(element.pos()))
                .thenComparing(Element::listIndex)
                .thenComparing(Element::pos));
    for (int i = 0; i < nums.size(); ++i) {
      elements.add(new Element(i, 0));
    }

    while (true) {
      int currentLower = nums.get(elements.first().listIndex()).get(elements.first().pos());
      int currentUpper = nums.get(elements.last().listIndex()).get(elements.last().pos());
      if (currentUpper - currentLower < upper - lower
          || (currentUpper - currentLower == upper - lower && currentLower < lower)) {
        lower = currentLower;
        upper = currentUpper;
      }

      Element element = elements.removeFirst();
      if (element.pos() == nums.get(element.listIndex()).size() - 1) {
        break;
      }

      elements.add(new Element(element.listIndex(), element.pos() + 1));
    }

    return new int[] {lower, upper};
  }
}

record Element(int listIndex, int pos) {}
