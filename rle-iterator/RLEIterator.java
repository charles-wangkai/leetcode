public class RLEIterator {
	private int[] A;
	int index = 0;

	public RLEIterator(int[] A) {
		this.A = A;
	}

	public int next(int n) {
		while (n > 0) {
			if (index == A.length) {
				return -1;
			}

			if (A[index] == 0) {
				index += 2;
			} else {
				int subtrahend = Math.min(n, A[index]);
				n -= subtrahend;
				A[index] -= subtrahend;
			}
		}

		return A[index + 1];
	}
}

// Your RLEIterator object will be instantiated and called as such:
// RLEIterator obj = new RLEIterator(A);
// int param_1 = obj.next(n);
