import java.util.Comparator;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
    NavigableSet<Range> ranges = new TreeSet<>(Comparator.comparing(range -> range.left));
    SortedMap<Integer, Set<Range>> lengthToRanges = new TreeMap<>();

    int left = 0;
    for (int i = 1; i <= s.length(); ++i) {
      if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
        Range range = new Range(left, i - 1, s.charAt(i - 1));
        ranges.add(range);
        addToMap(lengthToRanges, range);

        left = i;
      }
    }

    int[] result = new int[queryCharacters.length()];
    for (int i = 0; i < result.length; ++i) {
      char newLetter = queryCharacters.charAt(i);

      Range range = ranges.floor(new Range(queryIndices[i], -1, (char) 0));
      if (range.letter != newLetter) {
        ranges.remove(range);
        removeFromMap(lengthToRanges, range);

        if (range.left != queryIndices[i]) {
          Range lRange = new Range(range.left, queryIndices[i] - 1, range.letter);
          ranges.add(lRange);
          addToMap(lengthToRanges, lRange);
        }
        if (range.right != queryIndices[i]) {
          Range rRange = new Range(queryIndices[i] + 1, range.right, range.letter);
          ranges.add(rRange);
          addToMap(lengthToRanges, rRange);
        }

        int newLeft = queryIndices[i];
        Range leftRange = ranges.lower(range);
        if (leftRange != null && leftRange.right == newLeft - 1 && leftRange.letter == newLetter) {
          newLeft = leftRange.left;

          ranges.remove(leftRange);
          removeFromMap(lengthToRanges, leftRange);
        }

        int newRight = queryIndices[i];
        Range rightRange = ranges.higher(range);
        if (rightRange != null
            && rightRange.left == newRight + 1
            && rightRange.letter == newLetter) {
          newRight = rightRange.right;

          ranges.remove(rightRange);
          removeFromMap(lengthToRanges, rightRange);
        }

        Range newRange = new Range(newLeft, newRight, newLetter);
        ranges.add(newRange);
        addToMap(lengthToRanges, newRange);
      }

      result[i] = lengthToRanges.lastKey();
    }

    return result;
  }

  void addToMap(SortedMap<Integer, Set<Range>> lengthToRanges, Range range) {
    int length = range.getLength();
    lengthToRanges.putIfAbsent(length, new HashSet<>());
    lengthToRanges.get(length).add(range);
  }

  void removeFromMap(SortedMap<Integer, Set<Range>> lengthToRanges, Range range) {
    int length = range.getLength();
    lengthToRanges.get(length).remove(range);
    lengthToRanges.remove(length, Set.of());
  }
}

class Range {
  int left;
  int right;
  char letter;

  Range(int left, int right, char letter) {
    this.left = left;
    this.right = right;
    this.letter = letter;
  }

  int getLength() {
    return right - left + 1;
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right, letter);
  }

  @Override
  public boolean equals(Object obj) {
    Range other = (Range) obj;

    return left == other.left && right == other.right && letter == other.letter;
  }
}
