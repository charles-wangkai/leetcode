import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {
	public int minimumCost(int N, int[][] connections) {
		@SuppressWarnings("unchecked")
		List<Edge>[] edgeLists = new List[N];
		for (int i = 0; i < edgeLists.length; i++) {
			edgeLists[i] = new ArrayList<>();
		}

		for (int[] connection : connections) {
			Edge edge = new Edge(connection[0] - 1, connection[1] - 1, connection[2]);

			edgeLists[connection[0] - 1].add(edge);
			edgeLists[connection[1] - 1].add(edge);
		}

		int minCost = 0;
		boolean[] used = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>((edge1, edge2) -> Integer.compare(edge1.cost, edge2.cost));
		used[0] = true;
		for (Edge edge : edgeLists[0]) {
			pq.offer(edge);
		}

		while (!pq.isEmpty()) {
			Edge head = pq.poll();
			if (used[head.from] && used[head.to]) {
				continue;
			}

			minCost += head.cost;

			int node = !used[head.from] ? head.from : head.to;
			used[node] = true;

			for (Edge edge : edgeLists[node]) {
				if (!used[edge.from] || !used[edge.to]) {
					pq.offer(edge);
				}
			}
		}

		if (IntStream.range(0, N).anyMatch(i -> !used[i])) {
			return -1;
		}

		return minCost;
	}
}

class Edge {
	int from;
	int to;
	int cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}