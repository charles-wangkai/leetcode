import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ReconstructItinerary {
	List<String> itinerary;

	public List<String> findItinerary(String[][] tickets) {
		Map<String, SortedMap<String, Integer>> from2tos = new HashMap<String, SortedMap<String, Integer>>();
		for (String[] ticket : tickets) {
			String from = ticket[0];
			String to = ticket[1];

			addTicket(from2tos, from, to);
		}

		itinerary = null;
		List<String> currentItinerary = new ArrayList<String>();
		currentItinerary.add("JFK");
		search(from2tos, currentItinerary);
		return itinerary;
	}

	void search(Map<String, SortedMap<String, Integer>> from2tos, List<String> currentItinerary) {
		if (from2tos.isEmpty()) {
			itinerary = currentItinerary;
			return;
		}

		String from = currentItinerary.get(currentItinerary.size() - 1);
		if (from2tos.containsKey(from)) {
			TreeSet<String> tos = new TreeSet<String>(from2tos.get(from).keySet());
			for (String to : tos) {
				removeTicket(from2tos, from, to);
				currentItinerary.add(to);

				search(from2tos, currentItinerary);
				if (itinerary != null) {
					break;
				}

				currentItinerary.remove(currentItinerary.size() - 1);
				addTicket(from2tos, from, to);
			}
		}
	}

	void addTicket(Map<String, SortedMap<String, Integer>> from2tos, String from, String to) {
		if (!from2tos.containsKey(from)) {
			from2tos.put(from, new TreeMap<String, Integer>());
		}
		if (!from2tos.get(from).containsKey(to)) {
			from2tos.get(from).put(to, 0);
		}
		from2tos.get(from).put(to, from2tos.get(from).get(to) + 1);
	}

	void removeTicket(Map<String, SortedMap<String, Integer>> from2tos, String from, String to) {
		from2tos.get(from).put(to, from2tos.get(from).get(to) - 1);
		if (from2tos.get(from).get(to) == 0) {
			from2tos.get(from).remove(to);
		}
		if (from2tos.get(from).isEmpty()) {
			from2tos.remove(from);
		}
	}
}
