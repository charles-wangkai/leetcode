import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PyramidTransitionMatrix {
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		Map<String, List<Character>> base2tops = new HashMap<String, List<Character>>();
		for (String triple : allowed) {
			String base = triple.substring(0, 2);
			char top = triple.charAt(2);

			if (!base2tops.containsKey(base)) {
				base2tops.put(base, new ArrayList<Character>());
			}
			base2tops.get(base).add(top);
		}

		return search(base2tops, new HashSet<String>(), bottom, new StringBuilder());
	}

	boolean search(Map<String, List<Character>> base2tops, Set<String> visitedRows, String row, StringBuilder nextRow) {
		if (row.length() == 1) {
			return true;
		}

		if (nextRow.length() == row.length() - 1) {
			return search(base2tops, visitedRows, nextRow.toString(), new StringBuilder());
		}

		if (nextRow.length() == 0 && visitedRows.contains(row)) {
			return false;
		}

		String base = row.substring(nextRow.length(), nextRow.length() + 2);
		if (base2tops.containsKey(base)) {
			for (char top : base2tops.get(base)) {
				nextRow.append(top);
				if (search(base2tops, visitedRows, row, nextRow)) {
					return true;
				}
				nextRow.deleteCharAt(nextRow.length() - 1);
			}
		}

		if (nextRow.length() == 0) {
			visitedRows.add(row);
		}

		return false;
	}
}
