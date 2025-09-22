import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long maxTotalValue(int[] nums, int k) {
    RangeQuerier minRangeQuerier = new RangeQuerier(nums, Math::min);
    RangeQuerier maxRangeQuerier = new RangeQuerier(nums, Math::max);

    PriorityQueue<Element> pq =
        new PriorityQueue<>(Comparator.comparing(Element::maxMinDiff).reversed());
    for (int leftIndex = 0; leftIndex < nums.length; ++leftIndex) {
      pq.offer(
          new Element(
              leftIndex,
              nums.length - 1,
              maxRangeQuerier.query(leftIndex, nums.length - 1)
                  - minRangeQuerier.query(leftIndex, nums.length - 1)));
    }

    long result = 0;
    for (int i = 0; i < k; ++i) {
      Element head = pq.poll();
      result += head.maxMinDiff();

      if (head.rightIndex() != head.leftIndex()) {
        pq.offer(
            new Element(
                head.leftIndex(),
                head.rightIndex() - 1,
                maxRangeQuerier.query(head.leftIndex(), head.rightIndex() - 1)
                    - minRangeQuerier.query(head.leftIndex(), head.rightIndex() - 1)));
      }
    }

    return result;
  }
}

record Element(int leftIndex, int rightIndex, int maxMinDiff) {}

class RangeQuerier {
  Combiner combiner;
  int[][] sparseTable;

  RangeQuerier(int[] nums, Combiner combiner) {
    this.combiner = combiner;

    sparseTable = new int[nums.length][computeExponent(nums.length) + 1];
    for (int i = 0; i < sparseTable.length; ++i) {
      sparseTable[i][0] = nums[i];
    }
    for (int exponent = 1; exponent < sparseTable[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= sparseTable.length; ++i) {
        sparseTable[i][exponent] =
            combiner.combine(
                sparseTable[i][exponent - 1], sparseTable[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }

  int query(int leftIndex, int rightIndex) {
    int exponent = computeExponent(rightIndex - leftIndex + 1);

    return combiner.combine(
        sparseTable[leftIndex][exponent], sparseTable[rightIndex - (1 << exponent) + 1][exponent]);
  }
}

interface Combiner {
  int combine(int x, int y);
}