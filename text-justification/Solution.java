import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> fullJustify(String[] words, int L) {
		List<String> lines = new ArrayList<String>();
		List<String> lineWords = new ArrayList<String>();
		List<Integer> spaces = new ArrayList<Integer>();
		int length = 0;
		for (int i = 0; i < words.length; i++) {
			int nextLength = length + (length == 0 ? 0 : 1) + words[i].length();
			if (nextLength <= L) {
				if (length != 0) {
					spaces.add(1);
				}
				lineWords.add(words[i]);
				length = nextLength;
			} else {
				if (lineWords.size() > 1) {
					packExtraSpaces(spaces, L - length);
				} else {
					appendSpaces(lineWords, spaces, L - length);
				}
				lines.add(combineLine(lineWords, spaces));

				lineWords.clear();
				spaces.clear();
				length = 0;

				i--;
			}
		}
		if (!lineWords.isEmpty()) {
			appendSpaces(lineWords, spaces, L - length);
			lines.add(combineLine(lineWords, spaces));
		}
		return lines;
	}

	void packExtraSpaces(List<Integer> spaces, int spacesLeft) {
		int extra = spacesLeft / spaces.size();
		spacesLeft -= extra * spaces.size();
		for (int i = 0; i < spaces.size(); i++) {
			spaces.set(i, spaces.get(i) + extra + (i < spacesLeft ? 1 : 0));
		}
	}

	void appendSpaces(List<String> lineWords, List<Integer> spaces,
			int spacesLeft) {
		if (spacesLeft > 0) {
			if (lineWords.size() > spaces.size()) {
				spaces.add(spacesLeft);
			} else {
				spaces.set(spaces.size() - 1, spaces.get(spaces.size() - 1)
						+ spacesLeft);
			}
		}
	}

	String combineLine(List<String> lineWords, List<Integer> spaces) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < lineWords.size(); i++) {
			line.append(lineWords.get(i));
			if (i < spaces.size()) {
				for (int j = 0; j < spaces.get(i); j++) {
					line.append(' ');
				}
			}
		}
		return line.toString();
	}
}
