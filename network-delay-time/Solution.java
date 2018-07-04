import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {
	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, List<Edge>> source2edges = Arrays.stream(times).collect(
				Collectors.groupingBy(t -> t[0], Collectors.mapping(t -> new Edge(t[1], t[2]), Collectors.toList())));

		boolean[] visited = new boolean[N + 1];
		int remain = N;
		int lastTime = -1;
		PriorityQueue<Element> pq = new PriorityQueue<Element>((e1, e2) -> Integer.compare(e1.time, e2.time));
		pq.offer(new Element(K, 0));
		while (!pq.isEmpty()) {
			Element head = pq.poll();

			if (visited[head.node]) {
				continue;
			}

			visited[head.node] = true;
			lastTime = head.time;
			remain--;

			if (source2edges.containsKey(head.node)) {
				for (Edge edge : source2edges.get(head.node)) {
					pq.offer(new Element(edge.target, head.time + edge.time));
				}
			}
		}
		return (remain == 0) ? lastTime : -1;
	}
}

class Edge {
	int target;
	int time;

	Edge(int target, int time) {
		this.target = target;
		this.time = time;
	}
}

class Element {
	int node;
	int time;

	Element(int node, int time) {
		this.node = node;
		this.time = time;
	}
}