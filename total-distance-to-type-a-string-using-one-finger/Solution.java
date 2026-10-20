import static java.util.Map.entry;

import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<Character, Point> LETTER_TO_POINT =
      Map.ofEntries(
          entry('q', new Point(0, 0)),
          entry('w', new Point(0, 1)),
          entry('e', new Point(0, 2)),
          entry('r', new Point(0, 3)),
          entry('t', new Point(0, 4)),
          entry('y', new Point(0, 5)),
          entry('u', new Point(0, 6)),
          entry('i', new Point(0, 7)),
          entry('o', new Point(0, 8)),
          entry('p', new Point(0, 9)),
          entry('a', new Point(1, 0)),
          entry('s', new Point(1, 1)),
          entry('d', new Point(1, 2)),
          entry('f', new Point(1, 3)),
          entry('g', new Point(1, 4)),
          entry('h', new Point(1, 5)),
          entry('j', new Point(1, 6)),
          entry('k', new Point(1, 7)),
          entry('l', new Point(1, 8)),
          entry('z', new Point(2, 0)),
          entry('x', new Point(2, 1)),
          entry('c', new Point(2, 2)),
          entry('v', new Point(2, 3)),
          entry('b', new Point(2, 4)),
          entry('n', new Point(2, 5)),
          entry('m', new Point(2, 6)));

  public int totalDistance(String s) {
    return IntStream.range(0, s.length())
        .map(
            i ->
                computeDistance(
                    LETTER_TO_POINT.get(s.charAt(i)),
                    LETTER_TO_POINT.get((i == 0) ? 'a' : s.charAt(i - 1))))
        .sum();
  }

  int computeDistance(Point p1, Point p2) {
    return Math.abs(p1.r() - p2.r()) + Math.abs(p1.c() - p2.c());
  }
}

record Point(int r, int c) {}
