import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveDuplicateLetters {
	public String removeDuplicateLetters(String s) {
		List<LetterAndIndices> lis = IntStream.range(0, 26)
				.mapToObj(i -> new LetterAndIndices((char) (i + 'a'), new LinkedList<Integer>(), -1))
				.collect(Collectors.toList());

		for (int i = 0; i < s.length(); i++) {
			lis.get(s.charAt(i) - 'a').appendIndex(i);
		}

		lis.removeIf(li -> li.indices.isEmpty());

		StringBuilder sb = new StringBuilder();
		while (!lis.isEmpty()) {
			int minLastIndex = lis.stream().mapToInt(li -> li.lastIndex).min().getAsInt();

			char selectedLetter = 0;
			int selectedIndex = -1;
			for (LetterAndIndices li : lis) {
				if (li.indices.get(0) <= minLastIndex) {
					selectedLetter = li.letter;
					selectedIndex = li.indices.get(0);
					break;
				}
			}

			final char selectedLetterTemp = selectedLetter;
			lis.removeIf(li -> li.letter == selectedLetterTemp);

			final int selectedIndexTemp = selectedIndex;
			lis.forEach(li -> {
				Iterator<Integer> it = li.indices.iterator();
				while (it.hasNext()) {
					int index = it.next();

					if (index > selectedIndexTemp) {
						break;
					}

					it.remove();
				}
			});

			sb.append(selectedLetter);
		}
		return sb.toString();
	}
}

class LetterAndIndices {
	char letter;
	List<Integer> indices;
	int lastIndex;

	LetterAndIndices(char letter, List<Integer> indices, int lastIndex) {
		this.letter = letter;
		this.indices = indices;
		this.lastIndex = lastIndex;
	}

	void appendIndex(int index) {
		indices.add(index);
		lastIndex = index;
	}
}