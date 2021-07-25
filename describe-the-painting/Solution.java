import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public List<List<Long>> splitPainting(int[][] segments) {
    List<Element> elements = new ArrayList<>();
    for (int[] segment : segments) {
      elements.add(new Element(segment[0], true, segment[2]));
      elements.add(new Element(segment[1], false, segment[2]));
    }
    Collections.sort(
        elements, Comparator.comparing((Element e) -> e.pos).thenComparing(e -> e.startOrEnd));

    List<Element> merged = new ArrayList<>();
    for (Element element : elements) {
      if (!merged.isEmpty()
          && merged.get(merged.size() - 1).pos == element.pos
          && merged.get(merged.size() - 1).startOrEnd == element.startOrEnd) {
        merged.get(merged.size() - 1).delta += element.delta;
      } else {
        merged.add(element);
      }
    }

    List<List<Long>> result = new ArrayList<>();
    int prev = -1;
    long mix = 0;
    for (Element element : merged) {
      if (prev != -1 && element.pos != prev && mix != 0) {
        result.add(List.of((long) prev, (long) element.pos, mix));
      }

      if (element.startOrEnd) {
        mix += element.delta;
      } else {
        mix -= element.delta;
      }

      prev = element.pos;
    }

    return result;
  }
}

class Element {
  int pos;
  boolean startOrEnd;
  long delta;

  Element(int pos, boolean startOrEnd, long delta) {
    this.pos = pos;
    this.startOrEnd = startOrEnd;
    this.delta = delta;
  }
}
