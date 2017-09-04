import java.util.HashSet;
import java.util.Set;

public class BulbSwitcher_II {
	public int flipLights(int n, int m) {
		StringBuilder lights = new StringBuilder();
		for (int i = 0; i < n; i++) {
			lights.append("1");
		}

		Set<String> statusSet = new HashSet<String>();
		statusSet.add(lights.toString());

		for (int i = 0; i < m; i++) {
			Set<String> nextStatusSet = new HashSet<String>();
			for (String status : statusSet) {
				nextStatusSet.add(flip(status, 1, 1));
				nextStatusSet.add(flip(status, 2, 2));
				nextStatusSet.add(flip(status, 1, 2));
				nextStatusSet.add(flip(status, 1, 3));
			}

			statusSet = nextStatusSet;
		}
		return statusSet.size();
	}

	String flip(String status, int begin, int step) {
		StringBuilder flipped = new StringBuilder(status);
		for (int i = begin - 1; i < flipped.length(); i += step) {
			flipped.setCharAt(i, (char) ('0' + '1' - flipped.charAt(i)));
		}
		return flipped.toString();
	}
}
