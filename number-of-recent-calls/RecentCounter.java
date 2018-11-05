import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
	Queue<Integer> times = new LinkedList<>();

	public int ping(int t) {
		times.offer(t);

		while (times.peek() < t - 3000) {
			times.poll();
		}

		return times.size();
	}
}

// Your RecentCounter object will be instantiated and called as such:
// RecentCounter obj = new RecentCounter();
// int param_1 = obj.ping(t);
