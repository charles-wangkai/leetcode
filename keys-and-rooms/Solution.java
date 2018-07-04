import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int N = rooms.size();
		boolean[] visited = new boolean[N];
		visited[0] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		int remain = N - 1;

		while (remain > 0 && !queue.isEmpty()) {
			int room = queue.poll();

			for (int nextRoom : rooms.get(room)) {
				if (!visited[nextRoom]) {
					visited[nextRoom] = true;
					queue.offer(nextRoom);
					remain--;
				}
			}
		}

		return remain == 0;
	}
}
