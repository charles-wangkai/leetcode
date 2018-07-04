import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int shortestPathLength(int[][] graph) {
		int nodeNum = graph.length;

		int[][] steps = new int[1 << nodeNum][nodeNum];
		for (int i = 0; i < steps.length; i++) {
			Arrays.fill(steps[i], Integer.MAX_VALUE);
		}

		Queue<State> queue = new LinkedList<>();
		for (int i = 0; i < nodeNum; i++) {
			State state = new State(1 << i, i);

			steps[state.code][state.currentNode] = 0;
			queue.offer(state);
		}

		while (!queue.isEmpty()) {
			State state = queue.poll();

			for (int nextNode : graph[state.currentNode]) {
				int nextCode = state.code | (1 << nextNode);

				if (steps[nextCode][nextNode] == Integer.MAX_VALUE) {
					steps[nextCode][nextNode] = steps[state.code][state.currentNode] + 1;
					queue.offer(new State(nextCode, nextNode));
				}
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < nodeNum; i++) {
			result = Math.min(result, steps[(1 << nodeNum) - 1][i]);
		}
		return result;
	}
}

class State {
	int code;
	int currentNode;

	State(int code, int currentNode) {
		this.code = code;
		this.currentNode = currentNode;
	}
}