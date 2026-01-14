import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public double separateSquares(int[][] squares) {
    int[] ys =
        Arrays.stream(squares)
            .flatMapToInt(square -> IntStream.of(square[1], square[1] + square[2]))
            .sorted()
            .distinct()
            .toArray();
    Map<Integer, Integer> yToCompressed =
        IntStream.range(0, ys.length).boxed().collect(Collectors.toMap(i -> ys[i], i -> i));

    long totalArea = computeAboveArea(squares, ys, yToCompressed, ys[0]);

    int yIndex = -1;
    int lower = 0;
    int upper = ys.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeAboveArea(squares, ys, yToCompressed, ys[middle]) * 2 <= totalArea) {
        yIndex = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return ys[yIndex]
        - (totalArea / 2.0 - computeAboveArea(squares, ys, yToCompressed, ys[yIndex]))
            / computeXCovered(squares, ys[yIndex]);
  }

  int computeXCovered(int[][] squares, int lineY) {
    SortedMap<Integer, Integer> xToDelta = new TreeMap<>();
    for (int[] square : squares) {
      if (square[1] < lineY && square[1] + square[2] >= lineY) {
        xToDelta.put(square[0], xToDelta.getOrDefault(square[0], 0) + 1);
        xToDelta.put(square[0] + square[2], xToDelta.getOrDefault(square[0] + square[2], 0) - 1);
      }
    }

    int result = 0;
    int prevX = -1;
    int count = 0;
    for (int x : xToDelta.keySet()) {
      if (count != 0) {
        result += x - prevX;
      }

      count += xToDelta.get(x);

      prevX = x;
    }

    return result;
  }

  long computeAboveArea(int[][] squares, int[] ys, Map<Integer, Integer> yToCompressed, int lineY) {
    LazySegTree lazySegTree = new LazySegTree(ys);

    Event[] events =
        Arrays.stream(squares)
            .filter(square -> square[1] + square[2] > lineY)
            .flatMap(
                square -> {
                  int beginIndex = yToCompressed.get(Math.max(lineY, square[1]));
                  int endIndex = yToCompressed.get(square[1] + square[2]) - 1;

                  return Stream.of(
                      new Event(square[0], true, beginIndex, endIndex),
                      new Event(square[0] + square[2], false, beginIndex, endIndex));
                })
            .sorted(Comparator.comparing(Event::x))
            .toArray(Event[]::new);

    long result = 0;
    int prevX = -1;
    int eventIndex = 0;
    while (eventIndex != events.length) {
      result += (long) lazySegTree.root.getComputed() * (events[eventIndex].x() - prevX);

      prevX = events[eventIndex].x();
      while (eventIndex != events.length && events[eventIndex].x() == prevX) {
        lazySegTree.update(
            events[eventIndex].beginIndex(),
            events[eventIndex].endIndex(),
            events[eventIndex].addOrRemove() ? 1 : -1);

        ++eventIndex;
      }
    }

    return result;
  }
}

record Event(int x, boolean addOrRemove, int beginIndex, int endIndex) {}

class LazySegTree {
  Node root;

  LazySegTree(int[] values) {
    root = buildNode(values, 0, values.length - 2);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex, 0);

    if (beginIndex == endIndex) {
      node.length = values[beginIndex + 1] - values[beginIndex];
      node.covered = 0;
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(values, beginIndex, middleIndex);
      node.right = buildNode(values, middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
  }

  void update(int beginIndex, int endIndex, int delta) {
    update(beginIndex, endIndex, delta, root);
  }

  private void update(int beginIndex, int endIndex, int delta, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.apply(delta);
      } else {
        update(beginIndex, endIndex, delta, node.left);
        update(beginIndex, endIndex, delta, node.right);

        node.pull();
      }
    }
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int count;
    int length;
    int covered;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int count) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.count = count;
    }

    int getComputed() {
      return (count == 0) ? covered : length;
    }

    void apply(int delta) {
      count += delta;
    }

    void pull() {
      length = left.length + right.length;
      covered = left.getComputed() + right.getComputed();
    }
  }
}
