import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BusRoutes {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		Map<Integer, List<Integer>> stop2routes = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < routes.length; i++) {
			for (int stop : routes[i]) {
				if (!stop2routes.containsKey(stop)) {
					stop2routes.put(stop, new ArrayList<Integer>());
				}

				stop2routes.get(stop).add(i);
			}
		}

		Map<Integer, Integer> stop2distance = new HashMap<Integer, Integer>();
		stop2distance.put(S, 0);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(S);
		Set<Integer> usedRoutes = new HashSet<Integer>();
		while (!queue.isEmpty()) {
			int stop = queue.poll();

			if (stop == T) {
				return stop2distance.get(stop);
			}

			for (int route : stop2routes.get(stop)) {
				if (usedRoutes.contains(route)) {
					continue;
				}
				usedRoutes.add(route);

				for (int adjStop : routes[route]) {
					if (!stop2distance.containsKey(adjStop)) {
						stop2distance.put(adjStop, stop2distance.get(stop) + 1);
						queue.offer(adjStop);
					}
				}
			}
		}
		return -1;
	}
}
