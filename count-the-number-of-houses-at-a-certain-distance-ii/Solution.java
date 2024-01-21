import java.util.stream.IntStream;

class Solution {
  public long[] countOfPairs(int n, int x, int y) {
    if (x > y) {
      return countOfPairs(n, y, x);
    }

    return IntStream.range(0, n)
        .map(
            i ->
                computePairNumForLine(x - 1, i + 1)
                    + computePairNumForLine(n - y, i + 1)
                    + computePairNumForLineAndCircle(x - 1, y - x + 1, i + 1)
                    + computePairNumForLineAndCircle(n - y, y - x + 1, i + 1)
                    + computePairNumForCrossLine(x - 1, n - y, i + 1 - ((x == y) ? 0 : 1))
                    + computePairNumForCircle(y - x + 1, i + 1))
        .asLongStream()
        .toArray();
  }

  int computePairNumForLineAndCircle(int lineSize, int circleSize, int distance) {
    int maxCircleDistance = circleSize / 2;

    int lower = Math.max(1, distance - maxCircleDistance);
    int upper = Math.min(lineSize, distance);
    if (lower > upper) {
      return 0;
    }

    int rest = upper - lower + 1;
    int result = 0;
    if (distance >= lower && distance <= upper) {
      ++result;
      --rest;
    }
    if (maxCircleDistance != 0
        && distance - maxCircleDistance >= lower
        && distance - maxCircleDistance <= upper
        && circleSize % 2 == 0) {
      ++result;
      --rest;
    }

    result += rest * 2;
    result *= 2;

    return result;
  }

  int computePairNumForCrossLine(int size1, int size2, int distance) {
    if (distance < 2 || distance > size1 + size2) {
      return 0;
    }

    return (Math.min(size1, distance - 1) + Math.min(size2, distance - 1) - distance + 1) * 2;
  }

  int computePairNumForCircle(int size, int distance) {
    if (distance > size / 2) {
      return 0;
    }
    if (distance == size / 2 && size % 2 == 0) {
      return size;
    }

    return size * 2;
  }

  int computePairNumForLine(int size, int distance) {
    return Math.max(0, size - distance) * 2;
  }
}