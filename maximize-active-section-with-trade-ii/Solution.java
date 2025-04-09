// https://leetcode.com/problems/maximize-active-section-with-trade-ii/solutions/6593474/easy-understandable-short-explanation-c-python-java/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
    int totalOneCount = (int) s.chars().filter(c -> c == '1').count();

    List<Segment> segments = new ArrayList<>();
    int beginIndex = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i == s.length() || s.charAt(i) != s.charAt(beginIndex)) {
        segments.add(new Segment(beginIndex, i - beginIndex));
        beginIndex = i;
      }
    }

    int[][] sparseTable = new int[segments.size()][computeExponent(segments.size()) + 1];
    for (int i = 0; i + 2 < sparseTable.length; ++i) {
      if (s.charAt(segments.get(i).beginIndex()) == '0') {
        sparseTable[i][0] = segments.get(i).length() + segments.get(i + 2).length();
      }
    }
    for (int exponent = 1; exponent < sparseTable[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= sparseTable.length; ++i) {
        sparseTable[i][exponent] =
            Math.max(
                sparseTable[i][exponent - 1], sparseTable[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    return Arrays.stream(queries)
        .map(
            query -> {
              int left = query[0];
              int right = query[1];

              int leftIndex = findSegmentIndex(segments, left);
              int rightIndex = findSegmentIndex(segments, right);

              if (rightIndex - leftIndex + 1 <= 2) {
                return totalOneCount;
              }

              return totalOneCount
                  + Math.max(
                      computeMaxInRange(sparseTable, leftIndex + 1, rightIndex - 3),
                      Math.max(
                          computeExtra(s, segments, left, right, leftIndex, rightIndex, leftIndex),
                          computeExtra(
                              s, segments, left, right, leftIndex, rightIndex, rightIndex - 2)));
            })
        .toList();
  }

  int computeExtra(
      String s,
      List<Segment> segments,
      int left,
      int right,
      int leftIndex,
      int rightIndex,
      int segmentIndex) {
    if (s.charAt(segments.get(segmentIndex).beginIndex()) == '1') {
      return 0;
    }

    return getSegmentLength(segments, left, right, leftIndex, rightIndex, segmentIndex)
        + getSegmentLength(segments, left, right, leftIndex, rightIndex, segmentIndex + 2);
  }

  int getSegmentLength(
      List<Segment> segments,
      int left,
      int right,
      int leftIndex,
      int rightIndex,
      int segmentIndex) {
    if (segmentIndex == leftIndex) {
      return segments.get(leftIndex).length() - (left - segments.get(leftIndex).beginIndex());
    }
    if (segmentIndex == rightIndex) {
      return right - segments.get(rightIndex).beginIndex() + 1;
    }

    return segments.get(segmentIndex).length();
  }

  int findSegmentIndex(List<Segment> segments, int pos) {
    int result = -1;
    int lower = 0;
    int upper = segments.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (segments.get(middle).beginIndex() <= pos) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeMaxInRange(int[][] sparseTable, int leftIndex, int rightIndex) {
    if (leftIndex > rightIndex) {
      return 0;
    }

    int exponent = computeExponent(rightIndex - leftIndex + 1);

    return Math.max(
        sparseTable[leftIndex][exponent], sparseTable[rightIndex - (1 << exponent) + 1][exponent]);
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}

record Segment(int beginIndex, int length) {}
