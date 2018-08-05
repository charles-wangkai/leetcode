import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {
	public int reachableNodes(int[][] edges, int M, int N) {
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(graph[i], -1);
		}
		for (int[] edge : edges) {
			graph[edge[0]][edge[1]] = edge[2] + 1;
			graph[edge[1]][edge[0]] = edge[2] + 1;
		}

		boolean[] used = new boolean[N];
		int[] distances = new int[N];
		Arrays.fill(distances, -1);
		PriorityQueue<Element> pq = new PriorityQueue<>(
				(element1, element2) -> Integer.compare(element1.distance, element2.distance));

		pq.offer(new Element(0, 0));

		while (!pq.isEmpty()) {
			Element element = pq.poll();
			if (used[element.nextNode]) {
				continue;
			}

			used[element.nextNode] = true;
			distances[element.nextNode] = element.distance;

			for (int adjacent = 0; adjacent < N; adjacent++) {
				if (!used[adjacent] && graph[element.nextNode][adjacent] >= 0) {
					pq.offer(new Element(adjacent, distances[element.nextNode] + graph[element.nextNode][adjacent]));
				}
			}
		}

		int result = (int) IntStream.range(0, N).filter(i -> isReachable(distances, M, i)).count();
		for (int[] edge : edges) {
			int middleNum = 0;
			if (isReachable(distances, M, edge[0])) {
				middleNum += Math.min(edge[2], M - distances[edge[0]]);
			}
			if (isReachable(distances, M, edge[1])) {
				middleNum += Math.min(edge[2], M - distances[edge[1]]);
			}

			result += Math.min(edge[2], middleNum);
		}
		return result;
	}

	boolean isReachable(int[] distances, int M, int node) {
		return distances[node] >= 0 && distances[node] <= M;
	}
}

class Element {
	int nextNode;
	int distance;

	Element(int nextNode, int distance) {
		this.nextNode = nextNode;
		this.distance = distance;
	}
}