// https://www.baeldung.com/cs/minimal-manhattan-distance

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] beautifulPair(int[] nums1, int[] nums2) {
    Map<Point, List<Integer>> pointToIndices = new HashMap<>();
    for (int i = 0; i < nums1.length; ++i) {
      Point point = new Point(nums1[i], nums2[i]);
      pointToIndices.putIfAbsent(point, new ArrayList<>());
      pointToIndices.get(point).add(i);
    }

    Pair pair = null;
    for (List<Integer> indices : pointToIndices.values()) {
      if (indices.size() >= 2) {
        pair = combine(nums1, nums2, pair, new Pair(indices.get(0), indices.get(1)));
      }
    }
    if (pair == null) {
      pair = search(nums1, nums2, IntStream.range(0, nums1.length).toArray());
    }

    return new int[] {pair.index1(), pair.index2()};
  }

  Pair search(int[] x, int[] y, int[] indices) {
    if (indices.length < 2) {
      return null;
    }
    if (indices.length == 2) {
      return new Pair(indices[0], indices[1]);
    }

    int[] xSortedIndices =
        Arrays.stream(indices)
            .boxed()
            .sorted(Comparator.comparing(index -> x[index]))
            .mapToInt(Integer::intValue)
            .toArray();

    int xMiddle = x[xSortedIndices[xSortedIndices.length / 2 - 1]];
    int[] leftHalfIndices =
        IntStream.range(0, xSortedIndices.length / 2)
            .map(i -> xSortedIndices[i])
            .sorted()
            .toArray();
    int[] rightHalfIndices =
        IntStream.range(xSortedIndices.length / 2, xSortedIndices.length)
            .map(i -> xSortedIndices[i])
            .sorted()
            .toArray();

    Pair pair = combine(x, y, search(x, y, leftHalfIndices), search(x, y, rightHalfIndices));
    int distance = computeDistance(x, y, pair.index1(), pair.index2());

    int[] leftStripIndices =
        Arrays.stream(leftHalfIndices)
            .filter(index -> x[index] >= xMiddle - distance)
            .boxed()
            .sorted(Comparator.comparing(index -> y[index]))
            .mapToInt(Integer::intValue)
            .toArray();
    int[] rightStripIndices =
        Arrays.stream(rightHalfIndices)
            .filter(index -> x[index] <= xMiddle + distance)
            .boxed()
            .sorted(Comparator.comparing(index -> y[index]))
            .mapToInt(Integer::intValue)
            .toArray();

    int rightIndex = 0;
    for (int leftStripIndex : leftStripIndices) {
      while (rightIndex != rightStripIndices.length
          && y[rightStripIndices[rightIndex]] < y[leftStripIndex] - distance) {
        ++rightIndex;
      }

      for (int i = rightIndex;
          i < rightStripIndices.length && y[rightStripIndices[i]] <= y[leftStripIndex] + distance;
          ++i) {
        pair =
            combine(
                x,
                y,
                pair,
                new Pair(
                    Math.min(leftStripIndex, rightStripIndices[i]),
                    Math.max(leftStripIndex, rightStripIndices[i])));
      }
    }

    return pair;
  }

  Pair combine(int[] x, int[] y, Pair pair1, Pair pair2) {
    if (pair1 == null) {
      return pair2;
    }
    if (pair2 == null) {
      return pair1;
    }

    int distance1 = computeDistance(x, y, pair1.index1(), pair1.index2());
    int distance2 = computeDistance(x, y, pair2.index1(), pair2.index2());

    return (distance1 < distance2
            || (distance1 == distance2
                && (pair1.index1() < pair2.index1()
                    || (pair1.index1() == pair2.index1() && pair1.index2() <= pair2.index2()))))
        ? pair1
        : pair2;
  }

  int computeDistance(int[] x, int[] y, int index1, int index2) {
    return Math.abs(x[index1] - x[index2]) + Math.abs(y[index1] - y[index2]);
  }
}

record Point(int x, int y) {}

record Pair(int index1, int index2) {}
