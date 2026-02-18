// https://leetcode.com/problems/maximize-active-section-with-trade-ii/solutions/6593474/easy-understandable-short-explanation-c-python-java/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

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

    SparseTable sparseTable =
        new SparseTable(
            IntStream.range(0, segments.size())
                .map(
                    i ->
                        (i + 2 < segments.size() && s.charAt(segments.get(i).beginIndex()) == '0')
                            ? (segments.get(i).length() + segments.get(i + 2).length())
                            : 0)
                .toArray(),
            Math::max);

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

              int begin = leftIndex + 1;
              int end = rightIndex - 3;

              return totalOneCount
                  + Math.max(
                      (begin <= end) ? sparseTable.query(begin, end) : 0,
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
}

record Segment(int beginIndex, int length) {}

class SparseTable {
  int[][] st;
  BinaryOperator<Integer> binaryOperator;

  SparseTable(int[] values, BinaryOperator<Integer> binaryOperator) {
    st = new int[values.length][computeExponent(values.length) + 1];
    for (int i = 0; i < st.length; ++i) {
      st[i][0] = values[i];
    }
    for (int exponent = 1; exponent < st[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= st.length; ++i) {
        st[i][exponent] =
            binaryOperator.apply(st[i][exponent - 1], st[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    this.binaryOperator = binaryOperator;
  }

  int query(int beginIndex, int endIndex) {
    int exponent = computeExponent(endIndex - beginIndex + 1);

    return binaryOperator.apply(
        st[beginIndex][exponent], st[endIndex - (1 << exponent) + 1][exponent]);
  }

  private int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}
