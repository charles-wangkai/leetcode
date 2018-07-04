import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int integerReplacement(int n) {
		Queue<N_Step> queue = new LinkedList<N_Step>();
		queue.offer(new N_Step(n, 0));

		while (true) {
			N_Step head = queue.poll();
			if (head.n == 1) {
				return head.step;
			}

			if (head.n % 2 == 0) {
				queue.offer(new N_Step(head.n / 2, head.step + 1));
			} else {
				queue.offer(new N_Step(head.n + 1, head.step + 1));
				queue.offer(new N_Step(head.n - 1, head.step + 1));
			}
		}
	}
}

class N_Step {
	long n;
	int step;

	N_Step(long n, int step) {
		this.n = n;
		this.step = step;
	}
}