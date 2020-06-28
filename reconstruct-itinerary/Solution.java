import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, SortedMap<String, Integer>> fromToTos = new HashMap<>();
		for (List<String> ticket : tickets) {
			String from = ticket.get(0);
			String to = ticket.get(1);

			addTicket(fromToTos, from, to);
		}

		List<String> itinerary = new ArrayList<>();
		itinerary.add("JFK");

		return search(fromToTos, itinerary);
	}

	List<String> search(Map<String, SortedMap<String, Integer>> fromToTos, List<String> itinerary) {
		if (fromToTos.isEmpty()) {
			return itinerary;
		}

		String from = itinerary.get(itinerary.size() - 1);
		if (fromToTos.containsKey(from)) {
			List<String> tos = new ArrayList<>(fromToTos.get(from).keySet());
			for (String to : tos) {
				removeTicket(fromToTos, from, to);
				itinerary.add(to);

				List<String> subResult = search(fromToTos, itinerary);
				if (subResult != null) {
					return subResult;
				}

				itinerary.remove(itinerary.size() - 1);
				addTicket(fromToTos, from, to);
			}
		}

		return null;
	}

	void addTicket(Map<String, SortedMap<String, Integer>> fromToTos, String from, String to) {
		if (!fromToTos.containsKey(from)) {
			fromToTos.put(from, new TreeMap<>());
		}

		fromToTos.get(from).put(to, fromToTos.get(from).getOrDefault(to, 0) + 1);
	}

	void removeTicket(Map<String, SortedMap<String, Integer>> fromToTos, String from, String to) {
		fromToTos.get(from).put(to, fromToTos.get(from).get(to) - 1);
		fromToTos.get(from).remove(to, 0);

		if (fromToTos.get(from).isEmpty()) {
			fromToTos.remove(from);
		}
	}
}
