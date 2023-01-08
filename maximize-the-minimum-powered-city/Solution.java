import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public long maxPower(int[] stations, int r, int k) {
    long result = -1;
    long lower = 0;
    long upper = Arrays.stream(stations).asLongStream().sum() + k;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(stations, r, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  static boolean check(int[] stations, int r, int k, long power) {
    long needed = 0;
    Queue<Element> queue = new ArrayDeque<>();
    long sum = 0;
    long rangeSum = IntStream.range(0, r).map(i -> stations[i]).asLongStream().sum();
    for (int i = 0; i < stations.length; ++i) {
      if (i + r < stations.length) {
        rangeSum += stations[i + r];
      }

      while (!queue.isEmpty() && queue.peek().index() + r < i) {
        sum -= queue.poll().stationNum();
      }

      long diff = power - (rangeSum + sum);
      if (diff > 0) {
        queue.offer(new Element(Math.min(stations.length - 1, i + r), diff));
        sum += diff;

        needed += diff;
      }

      if (i - r >= 0) {
        rangeSum -= stations[i - r];
      }
    }

    return needed <= k;
  }
}

record Element(int index, long stationNum) {}
