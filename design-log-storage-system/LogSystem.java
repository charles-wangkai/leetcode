import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSystem {
	private Map<Integer, String> id2timestamp;

	public LogSystem() {
		id2timestamp = new HashMap<Integer, String>();
	}

	public void put(int id, String timestamp) {
		id2timestamp.put(id, timestamp);
	}

	public List<Integer> retrieve(String s, String e, String gra) {
		String sTrimmed = trim(s, gra);
		String eTrimmed = trim(e, gra);

		List<Integer> result = new ArrayList<Integer>();
		for (int id : id2timestamp.keySet()) {
			String timestampTrimmed = trim(id2timestamp.get(id), gra);

			if (timestampTrimmed.compareTo(sTrimmed) >= 0 && timestampTrimmed.compareTo(eTrimmed) <= 0) {
				result.add(id);
			}
		}
		return result;
	}

	private String trim(String timestamp, String gra) {
		if (gra.equals("Year")) {
			return timestamp.substring(0, 4);
		} else if (gra.equals("Month")) {
			return timestamp.substring(0, 7);
		} else if (gra.equals("Day")) {
			return timestamp.substring(0, 10);
		} else if (gra.equals("Hour")) {
			return timestamp.substring(0, 13);
		} else if (gra.equals("Minute")) {
			return timestamp.substring(0, 16);
		} else if (gra.equals("Second")) {
			return timestamp.substring(0, 19);
		} else {
			return null;
		}
	}
}

// Your LogSystem object will be instantiated and called as such:
// LogSystem obj = new LogSystem();
// obj.put(id,timestamp);
// List<Integer> param_2 = obj.retrieve(s,e,gra);
