import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

class Solution {
  public int[] minInterval(int[][] intervals, int[] queries) {
    NavigableSet<Element> candidates =
        new TreeSet<>(Comparator.comparing((Element e) -> e.query).thenComparing(e -> e.index));
    for (int i = 0; i < queries.length; ++i) {
      candidates.add(new Element(i, queries[i]));
    }

    int[] result = new int[queries.length];
    Arrays.fill(result, -1);

    Arrays.sort(intervals, Comparator.comparing(interval -> interval[1] - interval[0] + 1));
    for (int[] interval : intervals) {
      while (true) {
        Element candidate = candidates.ceiling(new Element(-1, interval[0]));
        if (candidate == null || candidate.query > interval[1]) {
          break;
        }

        result[candidate.index] = interval[1] - interval[0] + 1;
        candidates.remove(candidate);
      }
    }

    return result;
  }
}

class Element {
  int index;
  int query;

  Element(int index, int query) {
    this.index = index;
    this.query = query;
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, query);
  }

  @Override
  public boolean equals(Object obj) {
    Element other = (Element) obj;

    return index == other.index && query == other.query;
  }
}
