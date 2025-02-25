// https://leetcode.com/problems/maximize-the-distance-between-points-on-a-square/solutions/6458439/python-easy-to-understand-solution-binary-search-greedy/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int maxDistance(int side, int[][] points, int k) {
    int[] sortedIndices =
        IntStream.range(0, points.length)
            .boxed()
            .sorted(
                (i1, i2) -> {
                  int edge1 = getEdge(side, points[i1]);
                  int edge2 = getEdge(side, points[i2]);
                  if (edge1 != edge2) {
                    return Integer.compare(edge1, edge2);
                  }

                  if (edge1 == 0) {
                    return Integer.compare(points[i1][1], points[i2][1]);
                  }
                  if (edge1 == 1) {
                    return Integer.compare(points[i1][0], points[i2][0]);
                  }
                  if (edge1 == 2) {
                    return -Integer.compare(points[i1][1], points[i2][1]);
                  }

                  return -Integer.compare(points[i1][0], points[i2][0]);
                })
            .mapToInt(Integer::intValue)
            .toArray();

    int result = -1;
    int lower = 1;
    int upper = side;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(points, k, sortedIndices, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[][] points, int k, int[] sortedIndices, int distance) {
    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element(0, 0, 1));

    int maxLength = 1;
    for (int i = 1; i < sortedIndices.length; ++i) {
      int beginIndex = i;
      int length = 1;

      while (!queue.isEmpty()
          && computeDistance(points, sortedIndices[i], sortedIndices[queue.peek().lastIndex()])
              >= distance) {
        Element head = queue.poll();
        if (head.length() + 1 >= length
            && computeDistance(points, sortedIndices[i], sortedIndices[head.beginIndex()])
                >= distance) {
          if (head.length() + 1 > length) {
            length = head.length() + 1;
            maxLength = Math.max(maxLength, length);

            beginIndex = head.beginIndex();
          } else {
            beginIndex = Math.max(beginIndex, head.beginIndex());
          }
        }
      }

      queue.offer(new Element(i, beginIndex, length));
    }

    return maxLength >= k;
  }

  int computeDistance(int[][] points, int index1, int index2) {
    return Math.abs(points[index1][0] - points[index2][0])
        + Math.abs(points[index1][1] - points[index2][1]);
  }

  int getEdge(int side, int[] point) {
    if (point[0] == 0) {
      return 0;
    }
    if (point[1] == side) {
      return 1;
    }
    if (point[0] == side) {
      return 2;
    }

    return 3;
  }
}

record Element(int lastIndex, int beginIndex, int length) {}
