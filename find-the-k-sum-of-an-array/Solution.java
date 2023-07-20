import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public long kSum(int[] nums, int k) {
    List<Long> posSums =
        buildPosSums(k, Arrays.stream(nums).filter(x -> x >= 0).sorted().toArray());
    List<Long> negSums = buildNegSums(k, Arrays.stream(nums).filter(x -> x < 0).sorted().toArray());

    PriorityQueue<Long> sums = new PriorityQueue<>();
    for (long posSum : posSums) {
      for (long negSum : negSums) {
        long sum = posSum + negSum;
        if (sums.size() < k || sum > sums.peek()) {
          sums.offer(sum);
          if (sums.size() == k + 1) {
            sums.poll();
          }
        }
      }
    }

    return sums.peek();
  }

  List<Long> buildPosSums(int k, int[] values) {
    PriorityQueue<Long> sums = new PriorityQueue<>();
    sums.offer(Arrays.stream(values).asLongStream().sum());
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::sum).reversed());
    pq.offer(new Element(-1, Arrays.stream(values).asLongStream().sum()));

    List<Long> result = new ArrayList<>();
    for (int i = 0; i < k && !pq.isEmpty(); ++i) {
      Element head = pq.poll();

      result.add(head.sum());

      for (int j = 0; head.index() + 1 + j < values.length; ++j) {
        long nextSum = head.sum() - values[head.index() + 1 + j];
        if (sums.size() == k && nextSum <= sums.peek()) {
          break;
        }

        sums.offer(nextSum);
        if (sums.size() == k + 1) {
          sums.poll();
        }
        pq.offer(new Element(head.index() + 1 + j, nextSum));
      }
    }

    return result;
  }

  List<Long> buildNegSums(int k, int[] values) {
    PriorityQueue<Long> sums = new PriorityQueue<>();
    sums.offer(0L);
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::sum).reversed());
    pq.offer(new Element(values.length, 0));

    List<Long> result = new ArrayList<>();
    for (int i = 0; i < k && !pq.isEmpty(); ++i) {
      Element head = pq.poll();

      result.add(head.sum());

      for (int j = 0; head.index() - 1 - j >= 0; ++j) {
        long nextSum = head.sum() + values[head.index() - 1 - j];
        if (sums.size() == k && nextSum <= sums.peek()) {
          break;
        }

        sums.offer(nextSum);
        if (sums.size() == k + 1) {
          sums.poll();
        }
        pq.offer(new Element(head.index() - 1 - j, nextSum));
      }
    }

    return result;
  }
}

record Element(int index, long sum) {}
