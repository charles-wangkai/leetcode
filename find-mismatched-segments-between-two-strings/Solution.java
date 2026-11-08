import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<String>> findMismatchedSegments(String s1, String s2) {
    List<Range> ranges = new ArrayList<>();
    for (int i = 0; i < s1.length(); ++i) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (!ranges.isEmpty() && ranges.getLast().endIndex + 1 == i) {
          ++ranges.getLast().endIndex;
        } else {
          ranges.add(new Range(i, i));
        }
      }
    }

    return ranges.stream()
        .map(range ->
            List.of(
                s1.substring(range.beginIndex, range.endIndex + 1),
                s2.substring(range.beginIndex, range.endIndex + 1)))
        .toList();
  }
}

class Range {
  int beginIndex;
  int endIndex;

  Range(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}