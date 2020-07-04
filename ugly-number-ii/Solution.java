import java.util.PriorityQueue;

class Solution {
	static final int[] FACTORS = { 2, 3, 5 };

	public int nthUglyNumber(int n) {
		PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1.number, e2.number));
		pq.offer(new Element(1, 0));

		for (int i = 0; i < n - 1; i++) {
			Element head = pq.poll();

			for (int j = head.maxFactorIndex; j < FACTORS.length; ++j) {
				pq.offer(new Element(head.number * FACTORS[j], j));
			}
		}

		return (int) pq.poll().number;
	}
}

class Element {
	long number;
	int maxFactorIndex;

	Element(long number, int maxFactorIndex) {
		this.number = number;
		this.maxFactorIndex = maxFactorIndex;
	}
}