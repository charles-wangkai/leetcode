import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public double new21Game(int n, int k, int maxPts) {
    double[] probs = new double[n + 1];
    probs[0] = 1;

    double queueSum = 0;
    Queue<Double> lastProbs = new ArrayDeque<>();
    for (int i = 1; i <= n; ++i) {
      if (i - 1 < k) {
        queueSum += probs[i - 1];
        lastProbs.offer(probs[i - 1]);
      }

      if (i >= maxPts + 1 && !lastProbs.isEmpty()) {
        queueSum -= lastProbs.poll();
      }

      probs[i] = queueSum / maxPts;
    }

    return IntStream.rangeClosed(k, n).mapToDouble(i -> probs[i]).sum();
  }
}
