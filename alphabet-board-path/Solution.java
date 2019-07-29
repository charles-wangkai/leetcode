import java.util.HashMap;
import java.util.Map;

public class Solution {
	static Map<Character, Position> letterToPosition = new HashMap<>();
	static {
		int r = 0;
		int c = 0;
		for (char letter = 'a'; letter <= 'z'; letter++) {
			letterToPosition.put(letter, new Position(r, c));

			c++;
			if (c == 5) {
				r++;
				c = 0;
			}
		}
	}

	public String alphabetBoardPath(String target) {
		Position position = new Position(0, 0);
		StringBuilder result = new StringBuilder();
		for (char letter : target.toCharArray()) {
			Position nextPosition = letterToPosition.get(letter);

			while (!(position.r == nextPosition.r && position.c == nextPosition.c)) {
				if (nextPosition.r < position.r) {
					result.append('U');
					position.r--;
				} else if (nextPosition.r > position.r && (nextPosition.r != 5 || position.c == 0)) {
					result.append('D');
					position.r++;
				} else if (position.c > nextPosition.c) {
					result.append('L');
					position.c--;
				} else {
					result.append('R');
					position.c++;
				}
			}

			result.append('!');
		}

		return result.toString();
	}
}

class Position {
	int r;
	int c;

	Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
}