import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.BinaryOperator;

class Solution {
  public long maxTotalValue(int[] nums, int k) {
    SparseTable minSparseTable = new SparseTable(nums, Math::min);
    SparseTable maxSparseTable = new SparseTable(nums, Math::max);

    PriorityQueue<Element> pq =
        new PriorityQueue<>(Comparator.comparing(Element::maxMinDiff).reversed());
    for (int leftIndex = 0; leftIndex < nums.length; ++leftIndex) {
      pq.offer(
          new Element(
              leftIndex,
              nums.length - 1,
              maxSparseTable.query(leftIndex, nums.length - 1)
                  - minSparseTable.query(leftIndex, nums.length - 1)));
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
                maxSparseTable.query(head.leftIndex(), head.rightIndex() - 1)
                    - minSparseTable.query(head.leftIndex(), head.rightIndex() - 1)));
      }
    }

    return result;
  }
}

record Element(int leftIndex, int rightIndex, int maxMinDiff) {}

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
