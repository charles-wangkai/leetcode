import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Solution {
	public double new21Game(int N, int K, int W) {
		double[] probs = new double[N + 1];
		probs[0] = 1;

		double lastProbsSum = 0;
		Queue<Double> lastProbs = new LinkedList<Double>();
		for (int i = 1; i <= N; i++) {
			if (i - 1 < K) {
				lastProbsSum += probs[i - 1];
				lastProbs.offer(probs[i - 1]);
			}

			if (i >= W + 1 && !lastProbs.isEmpty()) {
				lastProbsSum -= lastProbs.poll();
			}

			if (!lastProbs.isEmpty()) {
				probs[i] = lastProbsSum / W;
			}
		}

		return IntStream.rangeClosed(K, N).mapToDouble(i -> probs[i]).sum();
	}
}
