import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {
	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
		List<Integer>[] viaRedAdjLists = buildAdjLists(n, red_edges);
		List<Integer>[] viaBlueAdjLists = buildAdjLists(n, blue_edges);

		int[] endRedMinDistances = initMinDistances(n);
		int[] endBlueMinDistances = initMinDistances(n);

		PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.distance, e2.distance));
		pq.offer(new Element(0, 0, true));
		pq.offer(new Element(0, 0, false));

		while (!pq.isEmpty()) {
			Element head = pq.poll();

			if (head.endRedOrBlue) {
				if (head.distance < endRedMinDistances[head.node]) {
					endRedMinDistances[head.node] = head.distance;

					for (int viaBlueAdj : viaBlueAdjLists[head.node]) {
						pq.offer(new Element(viaBlueAdj, head.distance + 1, false));
					}
				}
			} else {
				if (head.distance < endBlueMinDistances[head.node]) {
					endBlueMinDistances[head.node] = head.distance;

					for (int viaRedAdj : viaRedAdjLists[head.node]) {
						pq.offer(new Element(viaRedAdj, head.distance + 1, true));
					}
				}
			}
		}

		return IntStream.range(0, n).map(i -> Math.min(endRedMinDistances[i], endBlueMinDistances[i]))
				.map(x -> (x == Integer.MAX_VALUE) ? -1 : x).toArray();
	}

	int[] initMinDistances(int n) {
		int[] minDistances = new int[n];
		Arrays.fill(minDistances, Integer.MAX_VALUE);

		return minDistances;
	}

	List<Integer>[] buildAdjLists(int n, int[][] edges) {
		@SuppressWarnings("unchecked")
		List<Integer>[] adjLists = new List[n];
		for (int i = 0; i < adjLists.length; i++) {
			adjLists[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			adjLists[edge[0]].add(edge[1]);
		}

		return adjLists;
	}
}

class Element {
	int node;
	int distance;
	boolean endRedOrBlue;

	Element(int node, int distance, boolean endRedOrBlue) {
		this.node = node;
		this.distance = distance;
		this.endRedOrBlue = endRedOrBlue;
	}
}