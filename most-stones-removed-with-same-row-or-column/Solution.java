import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int removeStones(int[][] stones) {
		Stone[] stoneArray = new Stone[stones.length];
		for (int i = 0; i < stoneArray.length; i++) {
			stoneArray[i] = new Stone(stones[i][0], stones[i][1]);
		}

		for (int i = 0; i < stones.length; i++) {
			for (int j = i + 1; j < stones.length; j++) {
				if (stoneArray[i].x == stoneArray[j].x || stoneArray[i].y == stoneArray[j].y) {
					stoneArray[i].adjacents.add(j);
					stoneArray[j].adjacents.add(i);
				}
			}
		}

		int result = 0;
		boolean[] visited = new boolean[stoneArray.length];
		for (int i = 0; i < stoneArray.length; i++) {
			if (visited[i]) {
				result++;
			} else {
				dfs(stoneArray, visited, i);
			}
		}
		return result;
	}

	void dfs(Stone[] stoneArray, boolean[] visited, int index) {
		visited[index] = true;

		for (int adjacent : stoneArray[index].adjacents) {
			if (!visited[adjacent]) {
				dfs(stoneArray, visited, adjacent);
			}
		}
	}
}

class Stone {
	int x;
	int y;
	List<Integer> adjacents = new ArrayList<>();

	Stone(int x, int y) {
		this.x = x;
		this.y = y;
	}
}