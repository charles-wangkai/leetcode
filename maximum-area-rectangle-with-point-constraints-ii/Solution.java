import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long maxRectangleArea(int[] xCoord, int[] yCoord) {
    Map<Integer, NavigableSet<Integer>> xToYs = new HashMap<>();
    Map<Integer, NavigableSet<Integer>> yToXs = new HashMap<>();
    for (int i = 0; i < xCoord.length; ++i) {
      xToYs.putIfAbsent(xCoord[i], new TreeSet<>());
      xToYs.get(xCoord[i]).add(yCoord[i]);

      yToXs.putIfAbsent(yCoord[i], new TreeSet<>());
      yToXs.get(yCoord[i]).add(xCoord[i]);
    }

    Map<Integer, Integer> yValueToCompressed = buildValueToCompressed(yCoord);

    Node segmentTree =
        buildSegmentTree(
            yValueToCompressed.values().stream().mapToInt(Integer::intValue).min().getAsInt(),
            yValueToCompressed.values().stream().mapToInt(Integer::intValue).max().getAsInt());

    long result = -1;
    int[] sortedXs =
        Arrays.stream(xCoord)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .distinct()
            .mapToInt(Integer::intValue)
            .toArray();
    for (int minX : sortedXs) {
      Integer minY = null;
      for (int maxY : xToYs.get(minX)) {
        if (minY != null) {
          Integer maxX = yToXs.get(minY).higher(minX);
          if (maxX != null
              && Objects.equals(yToXs.get(maxY).higher(minX), maxX)
              && query(
                      yValueToCompressed.get(minY) + 1,
                      yValueToCompressed.get(maxY) - 1,
                      segmentTree)
                  > maxX) {
            result = Math.max(result, (long) (maxX - minX) * (maxY - minY));
          }
        }

        minY = maxY;
      }

      for (int y : xToYs.get(minX)) {
        update(yValueToCompressed.get(y), minX, segmentTree);
      }
    }

    return result;
  }

  Map<Integer, Integer> buildValueToCompressed(int[] values) {
    int[] sorted = Arrays.stream(values).sorted().distinct().toArray();

    return IntStream.range(0, sorted.length)
        .boxed()
        .collect(Collectors.toMap(i -> sorted[i], i -> i * 2));
  }

  int query(int begin, int end, Node node) {
    if (node.begin > end || node.end < begin) {
      return Integer.MAX_VALUE;
    }
    if (node.begin >= begin && node.end <= end) {
      return node.minValue;
    }

    return Math.min(query(begin, end, node.left), query(begin, end, node.right));
  }

  void update(int pos, int value, Node node) {
    if (node.begin <= pos && node.end >= pos) {
      if (node.begin == node.end) {
        node.minValue = value;
      } else {
        update(pos, value, node.left);
        update(pos, value, node.right);
        node.minValue = Math.min(node.left.minValue, node.right.minValue);
      }
    }
  }

  Node buildSegmentTree(int begin, int end) {
    if (begin == end) {
      return new Node(begin, end, Integer.MAX_VALUE, null, null);
    }

    int middle = (begin + end) / 2;
    Node left = buildSegmentTree(begin, middle);
    Node right = buildSegmentTree(middle + 1, end);

    return new Node(begin, end, Math.min(left.minValue, right.minValue), left, right);
  }
}

class Node {
  int begin;
  int end;
  int minValue;
  Node left;
  Node right;

  Node(int begin, int end, int minValue, Node left, Node right) {
    this.begin = begin;
    this.end = end;
    this.minValue = minValue;
    this.left = left;
    this.right = right;
  }
}