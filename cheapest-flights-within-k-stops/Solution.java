import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		Map<Integer, Map<Integer, Integer>> flightMap = new HashMap<Integer, Map<Integer, Integer>>();
		for (int[] flight : flights) {
			int from = flight[0];
			int to = flight[1];
			int price = flight[2];

			if (!flightMap.containsKey(from)) {
				flightMap.put(from, new HashMap<Integer, Integer>());
			}
			flightMap.get(from).put(to, price);
		}

		PriorityQueue<City> pq = new PriorityQueue<City>((city1, city2) -> Integer.compare(city1.price, city2.price));
		pq.add(new City(src, 0, 0));
		while (!pq.isEmpty()) {
			City city = pq.poll();

			if (city.index == dst) {
				return city.price;
			}

			if (city.move <= K && flightMap.containsKey(city.index)) {
				for (int to : flightMap.get(city.index).keySet()) {
					int price = flightMap.get(city.index).get(to);

					pq.offer(new City(to, city.price + price, city.move + 1));
				}
			}
		}

		return -1;
	}
}

class City {
	int index;
	int price;
	int move;

	City(int index, int price, int move) {
		this.index = index;
		this.price = price;
		this.move = move;
	}
}