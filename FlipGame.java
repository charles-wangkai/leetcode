import java.util.ArrayList;
import java.util.List;

public class FlipGame {
	public List<String> generatePossibleNextMoves(String s) {
		List<String> states = new ArrayList<String>();
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
				states.add(s.substring(0, i) + "--" + s.substring(i + 2));
			}
		}
		return states;
	}
}
