import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MinimumCostToHireKWorkers {
	public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		Worker[] workers = IntStream.range(0, quality.length).mapToObj(i -> new Worker(quality[i], wage[i]))
				.toArray(Worker[]::new);

		Arrays.sort(workers,
				(worker1, worker2) -> Integer.compare(worker1.wage * worker2.quality, worker2.wage * worker1.quality));

		double minCost = Double.MAX_VALUE;
		PriorityQueue<Worker> group = new PriorityQueue<>(
				(worker1, worker2) -> Integer.compare(worker2.quality, worker1.quality));
		int qualitySum = 0;
		for (Worker worker : workers) {
			if (group.size() == K && worker.quality < group.peek().quality) {
				qualitySum -= group.poll().quality;
			}

			if (group.size() < K) {
				group.offer(worker);
				qualitySum += worker.quality;
			}

			if (group.size() == K) {
				minCost = Math.min(minCost, (double) qualitySum * worker.wage / worker.quality);
			}
		}
		return minCost;
	}
}

class Worker {
	int quality;
	int wage;

	Worker(int quality, int wage) {
		this.quality = quality;
		this.wage = wage;
	}
}