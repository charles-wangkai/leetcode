import java.util.PriorityQueue;

public class UglyNumber_II {
	static final int[] FACTORS = { 2, 3, 5 };

	public int nthUglyNumber(int n) {
		PriorityQueue<Number_MaxFactor> pq = new PriorityQueue<Number_MaxFactor>(
				(nm1, nm2) -> (int) Math.signum(nm1.number - nm2.number));
		pq.offer(new Number_MaxFactor(1, 1));
		for (int i = 0; i < n - 1; i++) {
			Number_MaxFactor head = pq.poll();

			for (int factor : FACTORS) {
				if (factor >= head.maxFactor) {
					pq.offer(new Number_MaxFactor(head.number * factor, factor));
				}
			}
		}
		return (int) pq.poll().number;
	}
}

class Number_MaxFactor {
	long number;
	int maxFactor;

	Number_MaxFactor(long number, int maxFactor) {
		this.number = number;
		this.maxFactor = maxFactor;
	}
}