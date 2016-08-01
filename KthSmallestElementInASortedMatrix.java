import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;

		PriorityQueue<Grid> pq = new PriorityQueue<Grid>((g1, g2) -> matrix[g1.r][g1.c] - matrix[g2.r][g2.c]);
		pq.offer(new Grid(0, 0));

		int result = 0;
		for (int i = 0; i < k; i++) {
			Grid head = pq.poll();
			result = matrix[head.r][head.c];

			if (head.c + 1 < col) {
				pq.offer(new Grid(head.r, head.c + 1));
			}
			if (head.c == 0 && head.r + 1 < row) {
				pq.offer(new Grid(head.r + 1, head.c));
			}
		}
		return result;
	}
}

class Grid {
	int r;
	int c;

	Grid(int r, int c) {
		this.r = r;
		this.c = c;
	}
}