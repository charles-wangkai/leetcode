import java.util.Arrays;
import java.util.Stack;

public class Solution {
	public String shortestSuperstring(String[] A) {
		int n = A.length;

		int[][] graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] = computeOverlap(A[i], A[j]);
				graph[j][i] = computeOverlap(A[j], A[i]);
			}
		}

		int[][] overlaps = new int[1 << n][n];
		int[][] paths = new int[1 << n][n];
		int last = -1;
		int maxOverlap = -1;
		for (int i = 1; i < (1 << n); i++) {
			Arrays.fill(overlaps[i], -1);

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					int prev = i - (1 << j);

					if (prev == 0) {
						overlaps[i][j] = 0;
					} else {
						for (int k = 0; k < n; k++) {
							if (overlaps[prev][k] != -1 && overlaps[prev][k] + graph[k][j] > overlaps[i][j]) {
								overlaps[i][j] = overlaps[prev][k] + graph[k][j];
								paths[i][j] = k;
							}
						}
					}
				}

				if (i == (1 << n) - 1 && overlaps[i][j] > maxOverlap) {
					maxOverlap = overlaps[i][j];
					last = j;
				}
			}
		}

		int cur = (1 << n) - 1;
		Stack<Integer> stack = new Stack<>();
		while (cur != 0) {
			stack.push(last);

			int nextLast = paths[cur][last];
			cur -= (1 << last);
			last = nextLast;
		}

		StringBuilder result = new StringBuilder();
		int prev = -1;
		while (!stack.empty()) {
			int current = stack.pop();

			if (prev == -1) {
				result.append(A[current]);
			} else {
				result.append(A[current].substring(graph[prev][current]));
			}

			prev = current;
		}
		return result.toString();
	}

	static int computeOverlap(String a, String b) {
		for (int beginIndex = 1;; beginIndex++) {
			if (b.startsWith(a.substring(beginIndex))) {
				return a.length() - beginIndex;
			}
		}
	}
}
