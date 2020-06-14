import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] edgeLists = new Map[n];
		for (int i = 0; i < edgeLists.length; ++i) {
			edgeLists[i] = new HashMap<>();
		}

		for (int[] flight : flights) {
			int from = flight[0];
			int to = flight[1];
			int price = flight[2];

			edgeLists[from].put(to, price);
		}

		PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.cost, c2.cost));
		pq.add(new City(src, 0, -1));

		while (!pq.isEmpty()) {
			City city = pq.poll();

			if (city.index == dst) {
				return city.cost;
			}

			if (city.stopCount + 1 <= K) {
				for (int to : edgeLists[city.index].keySet()) {
					pq.offer(new City(to, city.cost + edgeLists[city.index].get(to), city.stopCount + 1));
				}
			}
		}

		return -1;
	}
}

class City {
	int index;
	int cost;
	int stopCount;

	City(int index, int cost, int stopCount) {
		this.index = index;
		this.cost = cost;
		this.stopCount = stopCount;
	}
}