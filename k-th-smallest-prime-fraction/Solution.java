import java.util.PriorityQueue;

public class Solution {
  public int[] kthSmallestPrimeFraction(int[] A, int K) {
    PriorityQueue<FractionIndices> pq =
        new PriorityQueue<>(
            (fi1, fi2) ->
                Integer.compare(
                    A[fi1.numeratorIndex] * A[fi2.denominatorIndex],
                    A[fi2.numeratorIndex] * A[fi1.denominatorIndex]));

    for (int i = 0; i < A.length - 1; i++) {
      pq.offer(new FractionIndices(i, A.length - 1));
    }

    for (int i = 0; i < K - 1; i++) {
      FractionIndices head = pq.poll();

      if (head.denominatorIndex - 1 > head.numeratorIndex) {
        pq.offer(new FractionIndices(head.numeratorIndex, head.denominatorIndex - 1));
      }
    }

    FractionIndices result = pq.peek();
    return new int[] {A[result.numeratorIndex], A[result.denominatorIndex]};
  }
}

class FractionIndices {
  int numeratorIndex;
  int denominatorIndex;

  FractionIndices(int numeratorIndex, int denominatorIndex) {
    this.numeratorIndex = numeratorIndex;
    this.denominatorIndex = denominatorIndex;
  }
}
