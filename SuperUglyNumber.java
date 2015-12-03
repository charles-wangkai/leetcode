import java.util.PriorityQueue;

public class SuperUglyNumber {
	public int nthSuperUglyNumber(int n, int[] primes) {
		PriorityQueue<UglyNumberAndPrimeIndex> pq = new PriorityQueue<UglyNumberAndPrimeIndex>(
				(ni1, ni2) -> Long.compare(ni1.uglyNumber, ni2.uglyNumber));
		pq.offer(new UglyNumberAndPrimeIndex(1, 0));

		long uglyNumber = -1;
		long maxUglyNumber = -1;
		for (int i = 0; i < n; i++) {
			UglyNumberAndPrimeIndex head = pq.poll();
			uglyNumber = head.uglyNumber;

			for (int j = head.primeIndex; j < primes.length
					&& !(i + 1 + pq.size() >= n && head.uglyNumber * primes[j] >= maxUglyNumber); j++) {
				pq.offer(new UglyNumberAndPrimeIndex(head.uglyNumber * primes[j], j));

				maxUglyNumber = Math.max(maxUglyNumber, head.uglyNumber * primes[j]);
			}
		}
		return (int) uglyNumber;
	}
}

class UglyNumberAndPrimeIndex {
	long uglyNumber;
	int primeIndex;

	UglyNumberAndPrimeIndex(long uglyNumber, int primeIndex) {
		this.uglyNumber = uglyNumber;
		this.primeIndex = primeIndex;
	}
}