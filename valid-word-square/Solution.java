import java.util.List;

public class Solution {
	public boolean validWordSquare(List<String> words) {
		int row = words.size();
		int col = words.stream().mapToInt(String::length).max().getAsInt();

		if (row != col) {
			return false;
		}

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (getChar(words, r, c) != getChar(words, c, r)) {
					return false;
				}
			}
		}
		return true;
	}

	Character getChar(List<String> words, int r, int c) {
		String word = words.get(r);
		return (c < word.length()) ? word.charAt(c) : null;
	}
}
