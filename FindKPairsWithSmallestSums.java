import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(
				(p1, p2) -> (nums1[p1.index1] + nums2[p1.index2]) - (nums1[p2.index1] + nums2[p2.index2]));

		List<int[]> result = new ArrayList<int[]>();
		if (nums1.length > 0 && nums2.length > 0) {
			pq.offer(new Pair(0, 0));
			while (!pq.isEmpty() && result.size() < k) {
				Pair head = pq.poll();

				result.add(new int[] { nums1[head.index1], nums2[head.index2] });

				if (head.index2 + 1 < nums2.length) {
					pq.offer(new Pair(head.index1, head.index2 + 1));
				}
				if (head.index2 == 0 && head.index1 + 1 < nums1.length) {
					pq.offer(new Pair(head.index1 + 1, 0));
				}
			}
		}
		return result;
	}
}

class Pair {
	int index1;
	int index2;

	Pair(int index1, int index2) {
		this.index1 = index1;
		this.index2 = index2;
	}
}