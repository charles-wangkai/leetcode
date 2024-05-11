import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int[] sortedWorkerIndices =
        IntStream.range(0, quality.length)
            .boxed()
            .sorted((i1, i2) -> Integer.compare(wage[i1] * quality[i2], wage[i2] * quality[i1]))
            .mapToInt(Integer::intValue)
            .toArray();

    double result = Double.MAX_VALUE;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    int qualitySum = 0;
    for (int workerIndex : sortedWorkerIndices) {
      if (pq.size() == k && quality[workerIndex] < pq.peek()) {
        qualitySum -= pq.poll();
      }

      if (pq.size() != k) {
        pq.offer(quality[workerIndex]);
        qualitySum += quality[workerIndex];
      }

      if (pq.size() == k) {
        result = Math.min(result, (double) qualitySum * wage[workerIndex] / quality[workerIndex]);
      }
    }

    return result;
  }
}
